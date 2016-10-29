package com.manhnv.indoordirection.service;

import com.manhnv.indoordirection.model.PathDirection;
import com.manhnv.indoordirection.model.Point;
import com.manhnv.indoordirection.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ManhNV on 10/29/16.
 */

public interface IApiService {
    @GET("/api/coordinate/list_room_point")
    @Headers("Content-Type:application/json")
    Call<ResponseModel<List<Point>>> getListRoom();

    @GET("/api/beacon/find_path")
    @Headers("Content-Type:application/json")
    Call<ResponseModel<PathDirection>> getPathDirection(@Query("from")int from,
                                                        @Query("to")int to);
}
