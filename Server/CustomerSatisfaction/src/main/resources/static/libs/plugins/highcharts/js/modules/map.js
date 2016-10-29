/*
 Highmaps JS v5.0.2 (2016-10-26)
 Highmaps as a plugin for Highcharts 4.1.x or Highstock 2.1.x (x being the patch version of this file)

 (c) 2011-2016 Torstein Honsi

 License: www.highcharts.com/license
 */
(function (t) {
    "object" === typeof module && module.exports ? module.exports = t : t(Highcharts)
})(function (t) {
    (function (a) {
        var f = a.Axis, n = a.each, k = a.pick;
        a = a.wrap;
        a(f.prototype, "getSeriesExtremes", function (a) {
            var e = this.isXAxis, w, f, r = [], v;
            e && n(this.series, function (a, b) {
                a.useMapGeometry && (r[b] = a.xData, a.xData = [])
            });
            a.call(this);
            e && (w = k(this.dataMin, Number.MAX_VALUE), f = k(this.dataMax, -Number.MAX_VALUE), n(this.series, function (a, b) {
                a.useMapGeometry && (w = Math.min(w, k(a.minX, w)), f = Math.max(f, k(a.maxX, w)), a.xData = r[b],
                    v = !0)
            }), v && (this.dataMin = w, this.dataMax = f))
        });
        a(f.prototype, "setAxisTranslation", function (a) {
            var p = this.chart, e = p.plotWidth / p.plotHeight, p = p.xAxis[0], k;
            a.call(this);
            "yAxis" === this.coll && void 0 !== p.transA && n(this.series, function (a) {
                a.preserveAspectRatio && (k = !0)
            });
            if (k && (this.transA = p.transA = Math.min(this.transA, p.transA), a = e / ((p.max - p.min) / (this.max - this.min)), a = 1 > a ? this : p, e = (a.max - a.min) * a.transA, a.pixelPadding = a.len - e, a.minPixelPadding = a.pixelPadding / 2, e = a.fixTo)) {
                e = e[1] - a.toValue(e[0], !0);
                e *= a.transA;
                if (Math.abs(e) > a.minPixelPadding || a.min === a.dataMin && a.max === a.dataMax)e = 0;
                a.minPixelPadding -= e
            }
        });
        a(f.prototype, "render", function (a) {
            a.call(this);
            this.fixTo = null
        })
    })(t);
    (function (a) {
        var f = a.Axis, n = a.Chart, k = a.color, e, p = a.each, w = a.extend, x = a.isNumber, r = a.Legend, v = a.LegendSymbolMixin, c = a.noop, b = a.merge, h = a.pick, q = a.wrap;
        e = a.ColorAxis = function () {
            this.init.apply(this, arguments)
        };
        w(e.prototype, f.prototype);
        w(e.prototype, {
            defaultColorAxisOptions: {
                lineWidth: 0,
                minPadding: 0,
                maxPadding: 0,
                gridLineWidth: 1,
                tickPixelInterval: 72,
                startOnTick: !0,
                endOnTick: !0,
                offset: 0,
                marker: {animation: {duration: 50}, width: .01},
                labels: {overflow: "justify"},
                minColor: "#e6ebf5",
                maxColor: "#003399",
                tickLength: 5,
                showInLegend: !0
            }, init: function (a, l) {
                var d = "vertical" !== a.options.legend.layout, g;
                this.coll = "colorAxis";
                g = b(this.defaultColorAxisOptions, {side: d ? 2 : 1, reversed: !d}, l, {
                    opposite: !d,
                    showEmpty: !1,
                    title: null
                });
                f.prototype.init.call(this, a, g);
                l.dataClasses && this.initDataClasses(l);
                this.initStops(l);
                this.horiz = d;
                this.zoomEnabled = !1;
                this.defaultLegendLength =
                    200
            }, tweenColors: function (b, a, d) {
                var m;
                a.rgba.length && b.rgba.length ? (b = b.rgba, a = a.rgba, m = 1 !== a[3] || 1 !== b[3], b = (m ? "rgba(" : "rgb(") + Math.round(a[0] + (b[0] - a[0]) * (1 - d)) + "," + Math.round(a[1] + (b[1] - a[1]) * (1 - d)) + "," + Math.round(a[2] + (b[2] - a[2]) * (1 - d)) + (m ? "," + (a[3] + (b[3] - a[3]) * (1 - d)) : "") + ")") : b = a.input || "none";
                return b
            }, initDataClasses: function (a) {
                var g = this, d, m = 0, h = this.chart.options.chart.colorCount, c = this.options, e = a.dataClasses.length;
                this.dataClasses = d = [];
                this.legendItems = [];
                p(a.dataClasses, function (a,
                                           l) {
                    a = b(a);
                    d.push(a);
                    a.color || ("category" === c.dataClassColor ? (a.colorIndex = m, m++, m === h && (m = 0)) : a.color = g.tweenColors(k(c.minColor), k(c.maxColor), 2 > e ? .5 : l / (e - 1)))
                })
            }, initStops: function (a) {
                this.stops = a.stops || [[0, this.options.minColor], [1, this.options.maxColor]];
                p(this.stops, function (a) {
                    a.color = k(a[1])
                })
            }, setOptions: function (a) {
                f.prototype.setOptions.call(this, a);
                this.options.crosshair = this.options.marker
            }, setAxisSize: function () {
                var a = this.legendSymbol, b = this.chart, d = b.options.legend || {}, m, h;
                a ? (this.left =
                    d = a.attr("x"), this.top = m = a.attr("y"), this.width = h = a.attr("width"), this.height = a = a.attr("height"), this.right = b.chartWidth - d - h, this.bottom = b.chartHeight - m - a, this.len = this.horiz ? h : a, this.pos = this.horiz ? d : m) : this.len = (this.horiz ? d.symbolWidth : d.symbolHeight) || this.defaultLegendLength
            }, toColor: function (a, b) {
                var d = this.stops, m, l, g = this.dataClasses, h, c;
                if (g)for (c = g.length; c--;) {
                    if (h = g[c], m = h.from, d = h.to, (void 0 === m || a >= m) && (void 0 === d || a <= d)) {
                        l = h.color;
                        b && (b.dataClass = c, b.colorIndex = h.colorIndex);
                        break
                    }
                } else {
                    this.isLog &&
                    (a = this.val2lin(a));
                    a = 1 - (this.max - a) / (this.max - this.min || 1);
                    for (c = d.length; c-- && !(a > d[c][0]););
                    m = d[c] || d[c + 1];
                    d = d[c + 1] || m;
                    a = 1 - (d[0] - a) / (d[0] - m[0] || 1);
                    l = this.tweenColors(m.color, d.color, a)
                }
                return l
            }, getOffset: function () {
                var a = this.legendGroup, b = this.chart.axisOffset[this.side];
                a && (this.axisParent = a, f.prototype.getOffset.call(this), this.added || (this.added = !0, this.labelLeft = 0, this.labelRight = this.width), this.chart.axisOffset[this.side] = b)
            }, setLegendColor: function () {
                var a, b = this.options, d = this.reversed;
                a = d ? 1 : 0;
                d = d ? 0 : 1;
                a = this.horiz ? [a, 0, d, 0] : [0, d, 0, a];
                this.legendColor = {
                    linearGradient: {x1: a[0], y1: a[1], x2: a[2], y2: a[3]},
                    stops: b.stops || [[0, b.minColor], [1, b.maxColor]]
                }
            }, drawLegendSymbol: function (a, b) {
                var d = a.padding, m = a.options, c = this.horiz, l = h(m.symbolWidth, c ? this.defaultLegendLength : 12), g = h(m.symbolHeight, c ? 12 : this.defaultLegendLength), e = h(m.labelPadding, c ? 16 : 30), m = h(m.itemDistance, 10);
                this.setLegendColor();
                b.legendSymbol = this.chart.renderer.rect(0, a.baseline - 11, l, g).attr({zIndex: 1}).add(b.legendGroup);
                this.legendItemWidth = l + d + (c ? m : e);
                this.legendItemHeight = g + d + (c ? e : 0)
            }, setState: c, visible: !0, setVisible: c, getSeriesExtremes: function () {
                var a;
                this.series.length && (a = this.series[0], this.dataMin = a.valueMin, this.dataMax = a.valueMax)
            }, drawCrosshair: function (a, b) {
                var d = b && b.plotX, m = b && b.plotY, c, h = this.pos, l = this.len;
                b && (c = this.toPixels(b[b.series.colorKey]), c < h ? c = h - 2 : c > h + l && (c = h + l + 2), b.plotX = c, b.plotY = this.len - c, f.prototype.drawCrosshair.call(this, a, b), b.plotX = d, b.plotY = m, this.cross && this.cross.addClass("highcharts-coloraxis-marker").add(this.legendGroup))
            },
            getPlotLinePath: function (a, b, d, m, c) {
                return x(c) ? this.horiz ? ["M", c - 4, this.top - 6, "L", c + 4, this.top - 6, c, this.top, "Z"] : ["M", this.left, c, "L", this.left - 6, c + 6, this.left - 6, c - 6, "Z"] : f.prototype.getPlotLinePath.call(this, a, b, d, m)
            }, update: function (a, c) {
                var d = this.chart, m = d.legend;
                p(this.series, function (a) {
                    a.isDirtyData = !0
                });
                a.dataClasses && m.allItems && (p(m.allItems, function (a) {
                    a.isDataClass && a.legendGroup.destroy()
                }), d.isDirtyLegend = !0);
                d.options[this.coll] = b(this.userOptions, a);
                f.prototype.update.call(this, a,
                    c);
                this.legendItem && (this.setLegendColor(), m.colorizeItem(this, !0))
            }, getDataClassLegendSymbols: function () {
                var b = this, h = this.chart, d = this.legendItems, m = h.options.legend, e = m.valueDecimals, q = m.valueSuffix || "", k;
                d.length || p(this.dataClasses, function (m, l) {
                    var g = !0, u = m.from, f = m.to;
                    k = "";
                    void 0 === u ? k = "\x3c " : void 0 === f && (k = "\x3e ");
                    void 0 !== u && (k += a.numberFormat(u, e) + q);
                    void 0 !== u && void 0 !== f && (k += " - ");
                    void 0 !== f && (k += a.numberFormat(f, e) + q);
                    d.push(w({
                        chart: h, name: k, options: {}, drawLegendSymbol: v.drawRectangle,
                        visible: !0, setState: c, isDataClass: !0, setVisible: function () {
                            g = this.visible = !g;
                            p(b.series, function (a) {
                                p(a.points, function (a) {
                                    a.dataClass === l && a.setVisible(g)
                                })
                            });
                            h.legend.colorizeItem(this, g)
                        }
                    }, m))
                });
                return d
            }, name: ""
        });
        p(["fill", "stroke"], function (b) {
            a.Fx.prototype[b + "Setter"] = function () {
                this.elem.attr(b, e.prototype.tweenColors(k(this.start), k(this.end), this.pos))
            }
        });
        q(n.prototype, "getAxes", function (a) {
            var b = this.options.colorAxis;
            a.call(this);
            this.colorAxis = [];
            b && new e(this, b)
        });
        q(r.prototype, "getAllItems",
            function (a) {
                var b = [], d = this.chart.colorAxis[0];
                d && d.options && (d.options.showInLegend && (d.options.dataClasses ? b = b.concat(d.getDataClassLegendSymbols()) : b.push(d)), p(d.series, function (a) {
                    a.options.showInLegend = !1
                }));
                return b.concat(a.call(this))
            });
        q(r.prototype, "colorizeItem", function (a, b, d) {
            a.call(this, b, d);
            d && b.legendColor && b.legendSymbol.attr({fill: b.legendColor})
        })
    })(t);
    (function (a) {
        var f = a.defined, n = a.each, k = a.noop;
        a.colorPointMixin = {
            isValid: function () {
                return null !== this.value
            }, setVisible: function (a) {
                var e =
                    this, k = a ? "show" : "hide";
                n(["graphic", "dataLabel"], function (a) {
                    if (e[a])e[a][k]()
                })
            }
        };
        a.colorSeriesMixin = {
            pointArrayMap: ["value"],
            axisTypes: ["xAxis", "yAxis", "colorAxis"],
            optionalAxis: "colorAxis",
            trackerGroups: ["group", "markerGroup", "dataLabelsGroup"],
            getSymbol: k,
            parallelArrays: ["x", "y", "value"],
            colorKey: "value",
            translateColors: function () {
                var a = this, p = this.options.nullColor, k = this.colorAxis, f = this.colorKey;
                n(this.data, function (e) {
                    var v = e[f];
                    if (v = e.options.color || (e.isNull ? p : k && void 0 !== v ? k.toColor(v,
                                e) : e.color || a.color))e.color = v
                })
            },
            colorAttribs: function (a) {
                var e = {};
                f(a.color) && (e[this.colorProp || "fill"] = a.color);
                return e
            }
        }
    })(t);
    (function (a) {
        function f(a) {
            a && (a.preventDefault && a.preventDefault(), a.stopPropagation && a.stopPropagation(), a.cancelBubble = !0)
        }

        var n = a.addEvent, k = a.Chart, e = a.doc, p = a.each, w = a.extend, x = a.merge, r = a.pick;
        a = a.wrap;
        w(k.prototype, {
            renderMapNavigation: function () {
                var a = this, c = this.options.mapNavigation, b = c.buttons, h, e, g, l = function (b) {
                    this.handler.call(a, b);
                    f(b)
                };
                if (r(c.enableButtons,
                        c.enabled) && !a.renderer.forExport)for (h in a.mapNavButtons = [], b)b.hasOwnProperty(h) && (g = x(c.buttonOptions, b[h]), e = a.renderer.button(g.text, 0, 0, l, void 0, void 0, void 0, 0, "zoomIn" === h ? "topbutton" : "bottombutton").addClass("highcharts-map-navigation").attr({
                    width: g.width,
                    height: g.height,
                    title: a.options.lang[h],
                    padding: g.padding,
                    zIndex: 5
                }).add(), e.handler = g.onclick, e.align(w(g, {
                    width: e.width,
                    height: 2 * e.height
                }), null, g.alignTo), n(e.element, "dblclick", f), a.mapNavButtons.push(e))
            }, fitToBox: function (a, c) {
                p([["x",
                    "width"], ["y", "height"]], function (b) {
                    var h = b[0];
                    b = b[1];
                    a[h] + a[b] > c[h] + c[b] && (a[b] > c[b] ? (a[b] = c[b], a[h] = c[h]) : a[h] = c[h] + c[b] - a[b]);
                    a[b] > c[b] && (a[b] = c[b]);
                    a[h] < c[h] && (a[h] = c[h])
                });
                return a
            }, mapZoom: function (a, c, b, h, e) {
                var g = this.xAxis[0], l = g.max - g.min, d = r(c, g.min + l / 2), m = l * a, l = this.yAxis[0], p = l.max - l.min, k = r(b, l.min + p / 2), p = p * a, d = this.fitToBox({
                    x: d - m * (h ? (h - g.pos) / g.len : .5),
                    y: k - p * (e ? (e - l.pos) / l.len : .5),
                    width: m,
                    height: p
                }, {x: g.dataMin, y: l.dataMin, width: g.dataMax - g.dataMin, height: l.dataMax - l.dataMin}), m =
                    d.x <= g.dataMin && d.width >= g.dataMax - g.dataMin && d.y <= l.dataMin && d.height >= l.dataMax - l.dataMin;
                h && (g.fixTo = [h - g.pos, c]);
                e && (l.fixTo = [e - l.pos, b]);
                void 0 === a || m ? (g.setExtremes(void 0, void 0, !1), l.setExtremes(void 0, void 0, !1)) : (g.setExtremes(d.x, d.x + d.width, !1), l.setExtremes(d.y, d.y + d.height, !1));
                this.redraw()
            }
        });
        a(k.prototype, "render", function (a) {
            var c = this, b = c.options.mapNavigation;
            c.renderMapNavigation();
            a.call(c);
            (r(b.enableDoubleClickZoom, b.enabled) || b.enableDoubleClickZoomTo) && n(c.container, "dblclick",
                function (a) {
                    c.pointer.onContainerDblClick(a)
                });
            r(b.enableMouseWheelZoom, b.enabled) && n(c.container, void 0 === e.onmousewheel ? "DOMMouseScroll" : "mousewheel", function (a) {
                c.pointer.onContainerMouseWheel(a);
                f(a);
                return !1
            })
        })
    })(t);
    (function (a) {
        var f = a.extend, n = a.pick, k = a.Pointer;
        a = a.wrap;
        f(k.prototype, {
            onContainerDblClick: function (a) {
                var e = this.chart;
                a = this.normalize(a);
                e.options.mapNavigation.enableDoubleClickZoomTo ? e.pointer.inClass(a.target, "highcharts-tracker") && e.hoverPoint && e.hoverPoint.zoomTo() : e.isInsidePlot(a.chartX -
                    e.plotLeft, a.chartY - e.plotTop) && e.mapZoom(.5, e.xAxis[0].toValue(a.chartX), e.yAxis[0].toValue(a.chartY), a.chartX, a.chartY)
            }, onContainerMouseWheel: function (a) {
                var e = this.chart, k;
                a = this.normalize(a);
                k = a.detail || -(a.wheelDelta / 120);
                e.isInsidePlot(a.chartX - e.plotLeft, a.chartY - e.plotTop) && e.mapZoom(Math.pow(e.options.mapNavigation.mouseWheelSensitivity, k), e.xAxis[0].toValue(a.chartX), e.yAxis[0].toValue(a.chartY), a.chartX, a.chartY)
            }
        });
        a(k.prototype, "zoomOption", function (a) {
            var e = this.chart.options.mapNavigation;
            a.apply(this, [].slice.call(arguments, 1));
            n(e.enableTouchZoom, e.enabled) && (this.pinchX = this.pinchHor = this.pinchY = this.pinchVert = this.hasZoom = !0)
        });
        a(k.prototype, "pinchTranslate", function (a, k, f, n, r, v, c) {
            a.call(this, k, f, n, r, v, c);
            "map" === this.chart.options.chart.type && this.hasZoom && (a = n.scaleX > n.scaleY, this.pinchTranslateDirection(!a, k, f, n, r, v, c, a ? n.scaleX : n.scaleY))
        })
    })(t);
    (function (a) {
        var f = a.colorPointMixin, n = a.each, k = a.extend, e = a.isNumber, p = a.map, w = a.merge, t = a.noop, r = a.pick, v = a.isArray, c = a.Point,
            b = a.Series, h = a.seriesType, q = a.seriesTypes, g = a.splat, l = void 0 !== a.doc.documentElement.style.vectorEffect;
        h("map", "scatter", {
            allAreas: !0,
            animation: !1,
            nullColor: "#f7f7f7",
            borderColor: "#cccccc",
            borderWidth: 1,
            marker: null,
            stickyTracking: !1,
            joinBy: "hc-key",
            dataLabels: {
                formatter: function () {
                    return this.point.value
                }, inside: !0, verticalAlign: "middle", crop: !1, overflow: !1, padding: 0
            },
            turboThreshold: 0,
            tooltip: {followPointer: !0, pointFormat: "{point.name}: {point.value}\x3cbr/\x3e"},
            states: {
                normal: {animation: !0}, hover: {
                    brightness: .2,
                    halo: null
                }, select: {color: "#cccccc"}
            }
        }, w(a.colorSeriesMixin, {
            type: "map",
            supportsDrilldown: !0,
            getExtremesFromAll: !0,
            useMapGeometry: !0,
            forceDL: !0,
            searchPoint: t,
            directTouch: !0,
            preserveAspectRatio: !0,
            pointArrayMap: ["value"],
            getBox: function (b) {
                var d = Number.MAX_VALUE, c = -d, h = d, g = -d, l = d, k = d, q = this.xAxis, f = this.yAxis, p;
                n(b || [], function (b) {
                    if (b.path) {
                        "string" === typeof b.path && (b.path = a.splitPath(b.path));
                        var m = b.path || [], q = m.length, f = !1, u = -d, n = d, v = -d, A = d, B = b.properties;
                        if (!b._foundBox) {
                            for (; q--;)e(m[q]) && (f ?
                                (u = Math.max(u, m[q]), n = Math.min(n, m[q])) : (v = Math.max(v, m[q]), A = Math.min(A, m[q])), f = !f);
                            b._midX = n + (u - n) * (b.middleX || B && B["hc-middle-x"] || .5);
                            b._midY = A + (v - A) * (b.middleY || B && B["hc-middle-y"] || .5);
                            b._maxX = u;
                            b._minX = n;
                            b._maxY = v;
                            b._minY = A;
                            b.labelrank = r(b.labelrank, (u - n) * (v - A));
                            b._foundBox = !0
                        }
                        c = Math.max(c, b._maxX);
                        h = Math.min(h, b._minX);
                        g = Math.max(g, b._maxY);
                        l = Math.min(l, b._minY);
                        k = Math.min(b._maxX - b._minX, b._maxY - b._minY, k);
                        p = !0
                    }
                });
                p && (this.minY = Math.min(l, r(this.minY, d)), this.maxY = Math.max(g, r(this.maxY,
                    -d)), this.minX = Math.min(h, r(this.minX, d)), this.maxX = Math.max(c, r(this.maxX, -d)), q && void 0 === q.options.minRange && (q.minRange = Math.min(5 * k, (this.maxX - this.minX) / 5, q.minRange || d)), f && void 0 === f.options.minRange && (f.minRange = Math.min(5 * k, (this.maxY - this.minY) / 5, f.minRange || d)))
            },
            getExtremes: function () {
                b.prototype.getExtremes.call(this, this.valueData);
                this.chart.hasRendered && this.isDirtyData && this.getBox(this.options.data);
                this.valueMin = this.dataMin;
                this.valueMax = this.dataMax;
                this.dataMin = this.minY;
                this.dataMax =
                    this.maxY
            },
            translatePath: function (a) {
                var b = !1, d = this.xAxis, c = this.yAxis, h = d.min, g = d.transA, d = d.minPixelPadding, l = c.min, k = c.transA, c = c.minPixelPadding, q, f = [];
                if (a)for (q = a.length; q--;)e(a[q]) ? (f[q] = b ? (a[q] - h) * g + d : (a[q] - l) * k + c, b = !b) : f[q] = a[q];
                return f
            },
            setData: function (d, c, h, l) {
                var m = this.options, q = this.chart.options.chart, k = q && q.map, f = m.mapData, u = m.joinBy, r = null === u, t = m.keys || this.pointArrayMap, z = [], x = {}, y, C = this.chart.mapTransforms;
                !f && k && (f = "string" === typeof k ? a.maps[k] : k);
                r && (u = "_i");
                u = this.joinBy =
                    g(u);
                u[1] || (u[1] = u[0]);
                d && n(d, function (a, b) {
                    var c = 0;
                    if (e(a))d[b] = {value: a}; else if (v(a)) {
                        d[b] = {};
                        !m.keys && a.length > t.length && "string" === typeof a[0] && (d[b]["hc-key"] = a[0], ++c);
                        for (var h = 0; h < t.length; ++h, ++c)t[h] && (d[b][t[h]] = a[c])
                    }
                    r && (d[b]._i = b)
                });
                this.getBox(d);
                if (this.chart.mapTransforms = C = q && q.mapTransforms || f && f["hc-transform"] || C)for (y in C)C.hasOwnProperty(y) && y.rotation && (y.cosAngle = Math.cos(y.rotation), y.sinAngle = Math.sin(y.rotation));
                if (f) {
                    "FeatureCollection" === f.type && (this.mapTitle = f.title,
                        f = a.geojson(f, this.type, this));
                    this.mapData = f;
                    this.mapMap = {};
                    for (y = 0; y < f.length; y++)q = f[y], k = q.properties, q._i = y, u[0] && k && k[u[0]] && (q[u[0]] = k[u[0]]), x[q[u[0]]] = q;
                    this.mapMap = x;
                    d && u[1] && n(d, function (a) {
                        x[a[u[1]]] && z.push(x[a[u[1]]])
                    });
                    m.allAreas ? (this.getBox(f), d = d || [], u[1] && n(d, function (a) {
                        z.push(a[u[1]])
                    }), z = "|" + p(z, function (a) {
                            return a && a[u[0]]
                        }).join("|") + "|", n(f, function (a) {
                        u[0] && -1 !== z.indexOf("|" + a[u[0]] + "|") || (d.push(w(a, {value: null})), l = !1)
                    })) : this.getBox(z)
                }
                b.prototype.setData.call(this,
                    d, c, h, l)
            },
            drawGraph: t,
            drawDataLabels: t,
            doFullTranslate: function () {
                return this.isDirtyData || this.chart.isResizing || this.chart.renderer.isVML || !this.baseTrans
            },
            translate: function () {
                var a = this, b = a.xAxis, c = a.yAxis, h = a.doFullTranslate();
                a.generatePoints();
                n(a.data, function (d) {
                    d.plotX = b.toPixels(d._midX, !0);
                    d.plotY = c.toPixels(d._midY, !0);
                    h && (d.shapeType = "path", d.shapeArgs = {d: a.translatePath(d.path)})
                });
                a.translateColors()
            },
            pointAttribs: function (a, b) {
                b = q.column.prototype.pointAttribs.call(this, a, b);
                a.isFading && delete b.fill;
                l ? b["vector-effect"] = "non-scaling-stroke" : b["stroke-width"] = "inherit";
                return b
            },
            drawPoints: function () {
                var a = this.xAxis, b = this.yAxis, c = this.group, h = this.chart.renderer, e, g = this.baseTrans;
                this.transformGroup || (this.transformGroup = h.g().attr({
                    scaleX: 1,
                    scaleY: 1
                }).add(c), this.transformGroup.survive = !0);
                this.doFullTranslate() ? (this.group = this.transformGroup, q.column.prototype.drawPoints.apply(this), this.group = c, n(this.points, function (a) {
                    a.graphic && (a.name && a.graphic.addClass("highcharts-name-" +
                        a.name.replace(/ /g, "-").toLowerCase()), a.properties && a.properties["hc-key"] && a.graphic.addClass("highcharts-key-" + a.properties["hc-key"].toLowerCase()))
                }), this.baseTrans = {
                    originX: a.min - a.minPixelPadding / a.transA,
                    originY: b.min - b.minPixelPadding / b.transA + (b.reversed ? 0 : b.len / b.transA),
                    transAX: a.transA,
                    transAY: b.transA
                }, this.transformGroup.animate({
                    translateX: 0,
                    translateY: 0,
                    scaleX: 1,
                    scaleY: 1
                })) : (e = a.transA / g.transAX, c = b.transA / g.transAY, a = a.toPixels(g.originX, !0), b = b.toPixels(g.originY, !0), .99 < e && 1.01 >
                e && .99 < c && 1.01 > c && (c = e = 1, a = Math.round(a), b = Math.round(b)), this.transformGroup.animate({
                    translateX: a,
                    translateY: b,
                    scaleX: e,
                    scaleY: c
                }));
                l || this.group.element.setAttribute("stroke-width", this.options[this.pointAttrToOptions && this.pointAttrToOptions["stroke-width"] || "borderWidth"] / (e || 1));
                this.drawMapDataLabels()
            },
            drawMapDataLabels: function () {
                b.prototype.drawDataLabels.call(this);
                this.dataLabelsGroup && this.dataLabelsGroup.clip(this.chart.clipRect)
            },
            render: function () {
                var a = this, c = b.prototype.render;
                a.chart.renderer.isVML &&
                3E3 < a.data.length ? setTimeout(function () {
                    c.call(a)
                }) : c.call(a)
            },
            animate: function (a) {
                var b = this.options.animation, d = this.group, c = this.xAxis, h = this.yAxis, e = c.pos, g = h.pos;
                this.chart.renderer.isSVG && (!0 === b && (b = {duration: 1E3}), a ? d.attr({
                    translateX: e + c.len / 2,
                    translateY: g + h.len / 2,
                    scaleX: .001,
                    scaleY: .001
                }) : (d.animate({translateX: e, translateY: g, scaleX: 1, scaleY: 1}, b), this.animate = null))
            },
            animateDrilldown: function (a) {
                var b = this.chart.plotBox, d = this.chart.drilldownLevels[this.chart.drilldownLevels.length - 1],
                    c = d.bBox, h = this.chart.options.drilldown.animation;
                a || (a = Math.min(c.width / b.width, c.height / b.height), d.shapeArgs = {
                    scaleX: a,
                    scaleY: a,
                    translateX: c.x,
                    translateY: c.y
                }, n(this.points, function (a) {
                    a.graphic && a.graphic.attr(d.shapeArgs).animate({
                        scaleX: 1,
                        scaleY: 1,
                        translateX: 0,
                        translateY: 0
                    }, h)
                }), this.animate = null)
            },
            drawLegendSymbol: a.LegendSymbolMixin.drawRectangle,
            animateDrillupFrom: function (a) {
                q.column.prototype.animateDrillupFrom.call(this, a)
            },
            animateDrillupTo: function (a) {
                q.column.prototype.animateDrillupTo.call(this,
                    a)
            }
        }), k({
            applyOptions: function (a, b) {
                a = c.prototype.applyOptions.call(this, a, b);
                b = this.series;
                var d = b.joinBy;
                b.mapData && ((d = void 0 !== a[d[1]] && b.mapMap[a[d[1]]]) ? (b.xyFromShape && (a.x = d._midX, a.y = d._midY), k(a, d)) : a.value = a.value || null);
                return a
            }, onMouseOver: function (a) {
                clearTimeout(this.colorInterval);
                if (null !== this.value)c.prototype.onMouseOver.call(this, a); else this.series.onMouseOut(a)
            }, zoomTo: function () {
                var a = this.series;
                a.xAxis.setExtremes(this._minX, this._maxX, !1);
                a.yAxis.setExtremes(this._minY,
                    this._maxY, !1);
                a.chart.redraw()
            }
        }, f))
    })(t);
    (function (a) {
        var f = a.seriesType;
        f("mapline", "map", {}, {
            type: "mapline",
            colorProp: "stroke",
            drawLegendSymbol: a.seriesTypes.line.prototype.drawLegendSymbol
        })
    })(t);
    (function (a) {
        var f = a.merge, n = a.Point;
        a = a.seriesType;
        a("mappoint", "scatter", {
            dataLabels: {
                enabled: !0, formatter: function () {
                    return this.point.name
                }, crop: !1, defer: !1, overflow: !1, style: {color: "#000000"}
            }
        }, {type: "mappoint", forceDL: !0}, {
            applyOptions: function (a, e) {
                a = void 0 !== a.lat && void 0 !== a.lon ? f(a, this.series.chart.fromLatLonToPoint(a)) :
                    a;
                return n.prototype.applyOptions.call(this, a, e)
            }
        })
    })(t);
    (function (a) {
        var f = a.merge, n = a.Point, k = a.seriesType, e = a.seriesTypes;
        e.bubble && k("mapbubble", "bubble", {
            animationLimit: 500,
            tooltip: {pointFormat: "{point.name}: {point.z}"}
        }, {
            xyFromShape: !0,
            type: "mapbubble",
            pointArrayMap: ["z"],
            getMapData: e.map.prototype.getMapData,
            getBox: e.map.prototype.getBox,
            setData: e.map.prototype.setData
        }, {
            applyOptions: function (a, k) {
                return a && void 0 !== a.lat && void 0 !== a.lon ? n.prototype.applyOptions.call(this, f(a, this.series.chart.fromLatLonToPoint(a)),
                    k) : e.map.prototype.pointClass.prototype.applyOptions.call(this, a, k)
            }, ttBelow: !1
        })
    })(t);
    (function (a) {
        var f = a.colorPointMixin, n = a.each, k = a.merge, e = a.noop, p = a.pick, w = a.Series, t = a.seriesType, r = a.seriesTypes;
        t("heatmap", "scatter", {
            animation: !1,
            borderWidth: 0,
            dataLabels: {
                formatter: function () {
                    return this.point.value
                }, inside: !0, verticalAlign: "middle", crop: !1, overflow: !1, padding: 0
            },
            marker: null,
            pointRange: null,
            tooltip: {pointFormat: "{point.x}, {point.y}: {point.value}\x3cbr/\x3e"},
            states: {
                normal: {animation: !0},
                hover: {halo: !1, brightness: .2}
            }
        }, k(a.colorSeriesMixin, {
            pointArrayMap: ["y", "value"],
            hasPointSpecificOptions: !0,
            supportsDrilldown: !0,
            getExtremesFromAll: !0,
            directTouch: !0,
            init: function () {
                var a;
                r.scatter.prototype.init.apply(this, arguments);
                a = this.options;
                a.pointRange = p(a.pointRange, a.colsize || 1);
                this.yAxis.axisPointRange = a.rowsize || 1
            },
            translate: function () {
                var a = this.options, c = this.xAxis, b = this.yAxis, h = function (a, b, c) {
                    return Math.min(Math.max(b, a), c)
                };
                this.generatePoints();
                n(this.points, function (e) {
                    var g =
                        (a.colsize || 1) / 2, l = (a.rowsize || 1) / 2, d = h(Math.round(c.len - c.translate(e.x - g, 0, 1, 0, 1)), -c.len, 2 * c.len), g = h(Math.round(c.len - c.translate(e.x + g, 0, 1, 0, 1)), -c.len, 2 * c.len), m = h(Math.round(b.translate(e.y - l, 0, 1, 0, 1)), -b.len, 2 * b.len), l = h(Math.round(b.translate(e.y + l, 0, 1, 0, 1)), -b.len, 2 * b.len);
                    e.plotX = e.clientX = (d + g) / 2;
                    e.plotY = (m + l) / 2;
                    e.shapeType = "rect";
                    e.shapeArgs = {
                        x: Math.min(d, g),
                        y: Math.min(m, l),
                        width: Math.abs(g - d),
                        height: Math.abs(l - m)
                    }
                });
                this.translateColors()
            },
            drawPoints: function () {
                r.column.prototype.drawPoints.call(this);
                n(this.points, function (a) {
                    a.graphic.attr(this.colorAttribs(a, a.state))
                }, this)
            },
            animate: e,
            getBox: e,
            drawLegendSymbol: a.LegendSymbolMixin.drawRectangle,
            alignDataLabel: r.column.prototype.alignDataLabel,
            getExtremes: function () {
                w.prototype.getExtremes.call(this, this.valueData);
                this.valueMin = this.dataMin;
                this.valueMax = this.dataMax;
                w.prototype.getExtremes.call(this)
            }
        }), f)
    })(t);
    (function (a) {
        function f(a, b) {
            var c, e, g, l = !1, d = a.x, m = a.y;
            a = 0;
            for (c = b.length - 1; a < b.length; c = a++)e = b[a][1] > m, g = b[c][1] > m, e !== g && d < (b[c][0] -
            b[a][0]) * (m - b[a][1]) / (b[c][1] - b[a][1]) + b[a][0] && (l = !l);
            return l
        }

        var n = a.Chart, k = a.each, e = a.extend, p = a.error, t = a.format, x = a.merge, r = a.win, v = a.wrap;
        n.prototype.transformFromLatLon = function (a, b) {
            if (void 0 === r.proj4)return p(21), {x: 0, y: null};
            a = r.proj4(b.crs, [a.lon, a.lat]);
            var c = b.cosAngle || b.rotation && Math.cos(b.rotation), e = b.sinAngle || b.rotation && Math.sin(b.rotation);
            a = b.rotation ? [a[0] * c + a[1] * e, -a[0] * e + a[1] * c] : a;
            return {
                x: ((a[0] - (b.xoffset || 0)) * (b.scale || 1) + (b.xpan || 0)) * (b.jsonres || 1) + (b.jsonmarginX ||
                0),
                y: (((b.yoffset || 0) - a[1]) * (b.scale || 1) + (b.ypan || 0)) * (b.jsonres || 1) - (b.jsonmarginY || 0)
            }
        };
        n.prototype.transformToLatLon = function (a, b) {
            if (void 0 === r.proj4)p(21); else {
                a = {
                    x: ((a.x - (b.jsonmarginX || 0)) / (b.jsonres || 1) - (b.xpan || 0)) / (b.scale || 1) + (b.xoffset || 0),
                    y: ((-a.y - (b.jsonmarginY || 0)) / (b.jsonres || 1) + (b.ypan || 0)) / (b.scale || 1) + (b.yoffset || 0)
                };
                var c = b.cosAngle || b.rotation && Math.cos(b.rotation), e = b.sinAngle || b.rotation && Math.sin(b.rotation);
                b = r.proj4(b.crs, "WGS84", b.rotation ? {x: a.x * c + a.y * -e, y: a.x * e + a.y * c} :
                    a);
                return {lat: b.y, lon: b.x}
            }
        };
        n.prototype.fromPointToLatLon = function (a) {
            var b = this.mapTransforms, c;
            if (b) {
                for (c in b)if (b.hasOwnProperty(c) && b[c].hitZone && f({
                        x: a.x,
                        y: -a.y
                    }, b[c].hitZone.coordinates[0]))return this.transformToLatLon(a, b[c]);
                return this.transformToLatLon(a, b["default"])
            }
            p(22)
        };
        n.prototype.fromLatLonToPoint = function (a) {
            var b = this.mapTransforms, c, e;
            if (!b)return p(22), {x: 0, y: null};
            for (c in b)if (b.hasOwnProperty(c) && b[c].hitZone && (e = this.transformFromLatLon(a, b[c]), f({
                    x: e.x,
                    y: -e.y
                }, b[c].hitZone.coordinates[0])))return e;
            return this.transformFromLatLon(a, b["default"])
        };
        a.geojson = function (a, b, h) {
            var c = [], g = [], l = function (a) {
                var b, d = a.length;
                g.push("M");
                for (b = 0; b < d; b++)1 === b && g.push("L"), g.push(a[b][0], -a[b][1])
            };
            b = b || "map";
            k(a.features, function (a) {
                var d = a.geometry, h = d.type, d = d.coordinates;
                a = a.properties;
                var f;
                g = [];
                "map" === b || "mapbubble" === b ? ("Polygon" === h ? (k(d, l), g.push("Z")) : "MultiPolygon" === h && (k(d, function (a) {
                    k(a, l)
                }), g.push("Z")), g.length && (f = {path: g})) : "mapline" === b ? ("LineString" === h ? l(d) : "MultiLineString" === h &&
                k(d, l), g.length && (f = {path: g})) : "mappoint" === b && "Point" === h && (f = {x: d[0], y: -d[1]});
                f && c.push(e(f, {name: a.name || a.NAME, properties: a}))
            });
            h && a.copyrightShort && (h.chart.mapCredits = t(h.chart.options.credits.mapText, {geojson: a}), h.chart.mapCreditsFull = t(h.chart.options.credits.mapTextFull, {geojson: a}));
            return c
        };
        v(n.prototype, "addCredits", function (a, b) {
            b = x(!0, this.options.credits, b);
            this.mapCredits && (b.href = null);
            a.call(this, b);
            this.credits && this.mapCreditsFull && this.credits.attr({title: this.mapCreditsFull})
        })
    })(t);
    (function (a) {
        function f(a, c, e, g, l, d, f, k) {
            return ["M", a + l, c, "L", a + e - d, c, "C", a + e - d / 2, c, a + e, c + d / 2, a + e, c + d, "L", a + e, c + g - f, "C", a + e, c + g - f / 2, a + e - f / 2, c + g, a + e - f, c + g, "L", a + k, c + g, "C", a + k / 2, c + g, a, c + g - k / 2, a, c + g - k, "L", a, c + l, "C", a, c + l / 2, a + l / 2, c, a + l, c, "Z"]
        }

        var n = a.Chart, k = a.defaultOptions, e = a.each, p = a.extend, t = a.merge, x = a.pick, r = a.Renderer, v = a.SVGRenderer, c = a.VMLRenderer;
        p(k.lang, {zoomIn: "Zoom in", zoomOut: "Zoom out"});
        k.mapNavigation = {
            buttonOptions: {
                alignTo: "plotBox", align: "left", verticalAlign: "top", x: 0, width: 18, height: 18,
                padding: 5
            }, buttons: {
                zoomIn: {
                    onclick: function () {
                        this.mapZoom(.5)
                    }, text: "+", y: 0
                }, zoomOut: {
                    onclick: function () {
                        this.mapZoom(2)
                    }, text: "-", y: 28
                }
            }, mouseWheelSensitivity: 1.1
        };
        a.splitPath = function (a) {
            var b;
            a = a.replace(/([A-Za-z])/g, " $1 ");
            a = a.replace(/^\s*/, "").replace(/\s*$/, "");
            a = a.split(/[ ,]+/);
            for (b = 0; b < a.length; b++)/[a-zA-Z]/.test(a[b]) || (a[b] = parseFloat(a[b]));
            return a
        };
        a.maps = {};
        v.prototype.symbols.topbutton = function (a, c, e, g, l) {
            return f(a - 1, c - 1, e, g, l.r, l.r, 0, 0)
        };
        v.prototype.symbols.bottombutton = function (a,
                                                     c, e, g, l) {
            return f(a - 1, c - 1, e, g, 0, 0, l.r, l.r)
        };
        r === c && e(["topbutton", "bottombutton"], function (a) {
            c.prototype.symbols[a] = v.prototype.symbols[a]
        });
        a.Map = a.mapChart = function (b, c, e) {
            var g = "string" === typeof b || b.nodeName, f = arguments[g ? 1 : 0], d = {
                endOnTick: !1,
                visible: !1,
                minPadding: 0,
                maxPadding: 0,
                startOnTick: !1
            }, h, k = a.getOptions().credits;
            h = f.series;
            f.series = null;
            f = t({
                chart: {panning: "xy", type: "map"}, credits: {
                    mapText: x(k.mapText, ' \u00a9 \x3ca href\x3d"{geojson.copyrightUrl}"\x3e{geojson.copyrightShort}\x3c/a\x3e'),
                    mapTextFull: x(k.mapTextFull, "{geojson.copyright}")
                }, xAxis: d, yAxis: t(d, {reversed: !0})
            }, f, {chart: {inverted: !1, alignTicks: !1}});
            f.series = h;
            return g ? new n(b, f, e) : new n(f, c)
        }
    })(t)
});