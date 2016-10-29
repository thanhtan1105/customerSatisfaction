package com.manhnv.indoordirection;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.manhnv.indoordirection.model.Path;
import com.manhnv.indoordirection.model.PathDirection;
import com.manhnv.indoordirection.model.Point;
import com.manhnv.indoordirection.model.ResponseModel;
import com.manhnv.indoordirection.service.RestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private int currFloor;
    private int totalFloor;
    private CustomImage ivMap;
    private Button btnNext;
    private Button btnPrevious;
    private List<Bitmap> mMaps;
    private List<Integer> mOriginalMap;
    private Point mCurrPoint;
    private RestService service;
    private Spinner spRoom;
    private RoomAdapter adapter;
    private float mMul;
    private boolean finding;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        event();

//        mCurrPoint = setPoint(12, 3.8f);
//        drawPoint(mCurrPoint);
//        Point newPoint = setPoint(6, 3.8f);
//        drawPoint(newPoint);
//        drawLine(mCurrPoint, newPoint);
    }

    private Point setPoint(float x, float y) {
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), mOriginalMap.get(currFloor - 1), dimensions);
        double height = dimensions.outHeight;
        double width = dimensions.outWidth;

        Bitmap bitmap = ((BitmapDrawable) ivMap.getDrawable()).getBitmap();

        float mul = (float) (bitmap.getWidth() * 36 / width);

        return new Point(x, y, mul);
    }

    private void drawPoint(Bitmap bitmap, Point point) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(point.getX() * mMul, point.getY() * mMul, 5, paint);
        ivMap.setImageBitmap(mMaps.get(currFloor - 1));
    }

    private void drawDestinationPoint(Bitmap bitmap, Point point) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.destination);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawBitmap(bitmap1.createScaledBitmap(bitmap1,50,50,false),point.getX() * mMul-25, point.getY() * mMul-25, paint);
        ivMap.setImageBitmap(mMaps.get(currFloor - 1));
    }

    private void drawLine(Bitmap bitmap,Point pointStart, Point pointEnd) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#00b3fd"));
        paint.setStrokeWidth(8);
        canvas.drawLine(pointStart.getX() * mMul, pointStart.getY() * mMul, pointEnd.getX() * mMul, pointEnd.getY() * mMul, paint);
        ivMap.setImageBitmap(mMaps.get(currFloor - 1));
    }

    private void getMul() {
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), mOriginalMap.get(currFloor - 1), dimensions);
        double height = dimensions.outHeight;
        double width = dimensions.outWidth;

        Bitmap bitmap = ((BitmapDrawable) ivMap.getDrawable()).getBitmap();

        mMul = (float) (bitmap.getWidth() * 36 / width);
    }

    private void event() {
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currFloor--;
                ivMap.setImageBitmap(mMaps.get(currFloor - 1));
                getSupportActionBar().setTitle("Floor " + currFloor);
                getMul();
//                Toast.makeText(MainActivity.this, "Current floor: "+currFloor, Toast.LENGTH_SHORT).show();
                if (currFloor == 1) {
                    btnPrevious.setEnabled(false);
                } else {
                    btnPrevious.setEnabled(true);
                }
                if (currFloor < totalFloor) {
                    btnNext.setEnabled(true);

                } else {
                    btnNext.setEnabled(false);
                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currFloor++;
                ivMap.setImageBitmap(mMaps.get(currFloor - 1));
                getSupportActionBar().setTitle("Floor " + currFloor);
                getMul();
//                Toast.makeText(MainActivity.this, "Current floor: "+currFloor, Toast.LENGTH_SHORT).show();
                if (currFloor == 1) {
                    btnPrevious.setEnabled(false);
                } else {
                    btnPrevious.setEnabled(true);
                }
                if (currFloor < totalFloor) {
                    btnNext.setEnabled(true);

                } else {
                    btnNext.setEnabled(false);
                }
            }
        });

        spRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0) {
                    Point room = adapter.getItem(position-1);
                    for (int i = 0; i < totalFloor; i++) {
                        mMaps.remove(mMaps.get(i));
                        Bitmap bitMap = BitmapFactory.decodeResource(getResources(), mOriginalMap.get(i));
                        bitMap = bitMap.copy(Bitmap.Config.ARGB_8888, true);
                        mMaps.add(i, bitMap);
                        drawCurrentPoint();
                        if (finding == false) {
                            findPath(room);
                        }
                    }

                    currFloor = mCurrPoint.getFloor();
                    ivMap.setImageBitmap(mMaps.get(currFloor - 1));
                    getSupportActionBar().setTitle("Floor " + currFloor);
                    getMul();
//                Toast.makeText(MainActivity.this, "Current floor: "+currFloor, Toast.LENGTH_SHORT).show();
                    if (currFloor == 1) {
                        btnPrevious.setEnabled(false);
                    } else {
                        btnPrevious.setEnabled(true);
                    }
                    if (currFloor < totalFloor) {
                        btnNext.setEnabled(true);

                    } else {
                        btnNext.setEnabled(false);
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListRoom();
            }
        });
    }

    private void findPath(final Point room) {
        finding = true;

        service.getApiService().getPathDirection(mCurrPoint.getId(),room.getId()).enqueue(new Callback<ResponseModel<PathDirection>>() {
            @Override
            public void onResponse(Call<ResponseModel<PathDirection>> call, Response<ResponseModel<PathDirection>> response) {
                finding = false;
                if (response.body().isSucceed()) {
                    if (response.body().getData().isFound()) {
                        drawPaths(response.body().getData(),room);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<PathDirection>> call, Throwable t) {
                finding = false;
                Toast.makeText(MainActivity.this, R.string.failure, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drawPaths(PathDirection data,Point room) {
        List<Path> paths  = data.getPaths();
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            List<Point> points = path.getPaths();
            for (int j = 0; j < points.size()-1; j++) {
                Point start = points.get(j);
                Point end = points.get(j+1);
                drawLine(mMaps.get(path.getFloor()-1),start,end);
            }
        }
        drawDestinationPoint(mMaps.get(room.getFloor() - 1), room);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.dialog_input_ip,null,false);
            final TextView txtIp = (TextView) view.findViewById(R.id.txt_ip);
            builder.setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            final AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txtIp.getText().toString().equals("")){
                        txtIp.setError("Please input ip");
                        txtIp.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake));
                        txtIp.requestFocus();
                    }else{
                        try {
                            service = new RestService(txtIp.getText().toString());
                            dialog.dismiss();
                            getListRoom();
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        service = new RestService();
        btnNext = (Button) findViewById(R.id.btn_next_floor);
        btnPrevious = (Button) findViewById(R.id.btn_previous_floor);
        ivMap = (CustomImage) findViewById(R.id.iv_map);
        spRoom = (Spinner) findViewById(R.id.spinner_room);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_refresh);
        btnPrevious.setEnabled(false);
        currFloor = 1;
        totalFloor = 2;
        mMaps = new ArrayList<>();
        Bitmap bitMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.floorone);
        bitMap1 = bitMap1.copy(Bitmap.Config.ARGB_8888, true);
        mMaps.add(bitMap1);
        Bitmap bitMap2 = BitmapFactory.decodeResource(getResources(), R.drawable.floortwo);
        bitMap2 = bitMap2.copy(Bitmap.Config.ARGB_8888, true);
        mMaps.add(bitMap2);
        mOriginalMap = new ArrayList<>();
        mOriginalMap.add(R.drawable.floorone);
        mOriginalMap.add(R.drawable.floortwo);
        ivMap.setImageBitmap(mMaps.get(currFloor - 1));
        getSupportActionBar().setTitle("Floor " + currFloor);
        createSpinner();
        getMul();
        mCurrPoint = new Point(5,"107",1,18,3.8f);
        drawCurrentPoint();

    }


    private void drawCurrentPoint() {

        Canvas canvas = new Canvas(mMaps.get(mCurrPoint.getFloor()-1));
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#5dabf4"));
        paint.setStrokeWidth(1);
        canvas.drawCircle(mCurrPoint.getX() * mMul, mCurrPoint.getY() * mMul, 15, paint);
        ivMap.setImageBitmap(mMaps.get(currFloor - 1));
    }


    private void createSpinner() {
        adapter = new RoomAdapter(this, R.layout.item_room, new ArrayList<Point>());
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spRoom.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListRoom();

    }

    private void getListRoom() {
        service.getApiService().getListRoom().enqueue(new Callback<ResponseModel<List<Point>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Point>>> call, Response<ResponseModel<List<Point>>> response) {
                if (refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }
                if (response.body().isSucceed()) {
                    adapter.setRooms(response.body().getData());
                } else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Point>>> call, Throwable t) {
                if (refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }
                Toast.makeText(MainActivity.this, R.string.failure, Toast.LENGTH_SHORT).show();
            }
        });

    }
}


