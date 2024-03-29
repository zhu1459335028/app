!function(t) {
    "function" == typeof define && define.amd && define.amd.jQuery ? define(["jquery"], t) : "undefined" != typeof jQuery && t(jQuery)
}(function(h) {
    "use strict";
    var p, u = "ontouchstart"in window, f = window.navigator.msPointerEnabled && !window.navigator.pointerEnabled || !1, v = window.navigator.pointerEnabled || window.navigator.msPointerEnabled || !1, g = u || v, m = g ? v ? f ? "MSPointerDown" : "pointerdown" : "touchstart" : "mousedown", y = g ? v ? f ? "MSPointerMove" : "pointermove" : "touchmove" : "mousemove", w = g ? v ? f ? "MSPointerUp" : "pointerup" : "touchend" : "mouseup", C = g ? null : "mouseenter", b = g ? null : "mouseleave", S = g ? v ? f ? "MSPointerCancel" : "pointercancel" : "touchcancel" : null, a = "transitionend webkitTransitionEnd oTransitionEnd msTransitionEnd", I = [], z = !1, T = 0, x = 0, k = 0, M = 0, t = (document.body || document.documentElement).style, q = !(void 0 === t.transform && void 0 === t.WebkitTransform && void 0 === t.MozTransform && void 0 === t.MsTransform && void 0 === t.OTransform || void 0 === t.transition && void 0 === t.WebkitTransition && void 0 === t.MozTransition && void 0 === t.MsTransition && void 0 === t.OTransition);
    function i(t, i) {
        var a = t.originalEvent ? t.originalEvent : t;
        return u && void 0 !== a.touches && 1 == a.touches.length ? a.touches[0][i] : void 0 !== t[i] ? t[i] : void 0 !== a[i] ? a[i] : 0
    }
    function O(t) {
        return i(t, "pageX")
    }
    function E(t) {
        return i(t, "pageY")
    }
    h.fn.extend({
        setTransition: function(t) {
            if (1 == q) {
                var i = 0 !== t ? "cubic-bezier(0.39, 0.575, 0.565, 1)" : "linear"
                    , a = t + "s"
                    , n = 0 !== t ? "all" : "none";
                this.css({
                    "-webkit-transition-property": n,
                    "-moz-transition-property": n,
                    "-ms-transition-property": n,
                    "-o-transition-property": n,
                    "transition-property": n,
                    "-webkit-transition-duration": a,
                    "-moz-transition-duration": a,
                    "-ms-transition-duration": a,
                    "-o-transition-duration": a,
                    "transition-duration": a,
                    "-webkit-transition-timing-function": i,
                    "-moz-transition-timing-function": i,
                    "-ms-transition-timing-function": i,
                    "-o-transition-timing-function": i,
                    "transition-timing-function": i
                })
            }
            return this
        },
        translateX: function(t, i) {
            var a = "";
            return -1 == t.toString().indexOf("px") && (a = "px"),
                1 == q ? (null != i && !0 === i && this.setTransition(0),
                    this.css({
                        "-webkit-transform": "translate3d(" + t + a + ", 0, 0)",
                        "-moz-transform": "translateX(" + t + a + ")",
                        "-ms-transform": "translateX(" + t + a + ")",
                        "-o-transform": "translateX(" + t + a + ")",
                        transform: "translateX(" + t + a + ")"
                    })) : this.css({
                    "margin-left": t
                }),
                this
        },
        animateX: function(t, i) {
            return 1 == q ? (this.setTransition(i / 1e3).translateX(t),
            this.hasClass("event-attached") || this.addClass("event-attached").on(a, function(t) {
                var i = h(t.target);
                i.hasClass("slider-slides") && (i.getSlider().slideComplete(),
                    i.setTransition(0))
            })) : this.stop(!0, !0).animate({
                "margin-left": t
            }, i, function() {
                h(this).getSlider().slideComplete()
            }),
                this
        },
        animateFade: function(t) {
            return 1 == q ? (this.setTransition(t / 1e3).css({
                "-webkit-opacity": 1,
                "-moz-opacity": 1,
                "-ms-opacity": 1,
                "-o-opacity": 1,
                opacity: 1
            }),
            this.hasClass("event-attached") || this.addClass("event-attached").on(a, function(t) {
                -1 != t.originalEvent.propertyName.indexOf("opacity") && 0 != h(this).css("z-index") && h(this).getSlider().slideComplete()
            })) : this.stop(!0, !0).fadeTo(t, 1, function() {
                h(this).getSlider().slideComplete()
            }),
                this
        }
    }),
        h.fn.extend({
            sliderInit: function(t) {
                var i = this.children().length;
                if (!(i <= 1 || 1 == this.data("initialized"))) {
                    this.hasClass("jSlider") || this.addClass("jSlider");
                    var a = {
                        navigation: "hover",
                        indicator: "always",
                        speed: 500,
                        delay: 5e3,
                        transition: "slide",
                        loop: !1,
                        group: 1
                    }
                        , n = {
                        navigation: this.data("navigation"),
                        indicator: this.data("indicator"),
                        speed: this.data("speed"),
                        delay: this.data("delay"),
                        transition: this.data("transition"),
                        loop: this.data("loop"),
                        group: this.data("group")
                    }
                        , e = h.extend({}, a, n, t);
                    (isNaN(e.speed) || e.speed < 0) && (e.speed = a.speed),
                    (isNaN(e.delay) || e.delay < 0) && (e.delay = a.delay),
                    "slide" != e.transition && "fade" != e.transition && (e.transition = a.transition),
                    "slide" != e.transition && (e.group = 1),
                    g && ("hover" == e.navigation && (e.navigation = "none"),
                    "hover" == e.indicator && (e.indicator = "none")),
                        i = Math.ceil(i / e.group),
                        this.data("settings", e);
                    var s = 0
                        , r = "> div.selected";
                    "" != window.location.hash && (r = window.location.hash + "," + r),
                    0 != this.find(r).length && (s = Math.ceil(this.find(r).index() / e.group),
                        this.find("> div.selected").removeClass("selected"),
                        this.find("> div:eq(" + s + ")").addClass("selected")),
                        this.find("[class*=appear-]").each(function() {
                            var t = h(this)
                                , i = parseInt(t.css("top"), 10)
                                , a = parseInt(t.css("left"), 10);
                            t.data("top", i).data("left", a),
                            t.hasClass("appear-top") && t.css("top", i - 15),
                            t.hasClass("appear-bottom") && t.css("top", i + 15),
                            t.hasClass("appear-left") && t.css("left", i - 15),
                            t.hasClass("appear-right") && t.css("left", a + 15),
                                t.css("opacity", 0),
                            q && t.addClass("cssAnimation")
                        }),
                        this.data("total", i).data("current", s).data("cancel-play", !1).data("map-index", I.length).data("initialized", !0).wrapInner('<div class="slider-content"><div class="slider-content-wrapper"><div class="slider-slides"></div></div></div>'),
                        this.find(".slider-slides").setTransition(0),
                        I.push({
                            slider: this,
                            sliderInterval: null,
                            showInterval: null
                        }),
                        1 != e.group ? this.find(".slider-slides > div").css("width", 100 / e.group + "%") : this.find("img:eq(0)").clone().prependTo(this),
                    (f || v) && this.css({
                        "-ms-scroll-chaining": "none",
                        "-ms-touch-action": "pan-y pinch-zoom",
                        "touch-action": "pan-y pinch-zoom"
                    }),
                    u && this.addClass("webkit-slider"),
                        "fade" != e.transition || "none" == e.navigation && "none" == e.indicator ? "none" == e.navigation && "none" == e.indicator && this.addClass("background-transition") : this.addClass("fade-transition"),
                    "none" == e.navigation && "none" == e.indicator || (this.find(".slider-content-wrapper").on(m, function(t) {
                        var i = h(this).getSlider()
                            , a = i.find(".slider-slides");
                        g || (t.preventDefault(),
                            a.addClass("grabbing")),
                            a.setTransition(0),
                            !(z = !0),
                            T = O(t),
                            x = E(t),
                            i.sliderStop(),
                            p = i
                    }).on(y, function(t) {
                        var i = h(this).getSlider()
                            , a = i.find(".slider-slides")
                            , n = i.data("settings")
                            , e = i.data("current")
                            , s = i.width();
                        1 == z && (g || t.preventDefault(),
                            k = O(t) - T,
                            M = E(t) - x,
                            Math.abs(M) > Math.abs(k) ? g && i.sliderCancelTouch() : (!0,
                                t.preventDefault(),
                            "slide" == n.transition && a.translateX(-e * s + k, !0)))
                    }).on(w + " " + S, function(t) {
                        var i = h(this).getSlider()
                            , a = i.find(".slider-slides")
                            , n = i.data("settings")
                            , e = i.data("current")
                            , s = (i.width(),
                            1);
                        a.removeClass("grabbing"),
                            35 < Math.abs(k) ? (s = 0 < k ? -1 : 1,
                                i.sliderGo(s)) : "slide" == n.transition && i.sliderUpdate(e),
                            i.sliderCancelTouch(),
                            p = null
                    }),
                        h(document).on({
                            mouseup: function(t) {
                                null != p && p.find(".slider-content-wrapper").trigger(w)
                            }
                        }),
                    null != C && null != b && this.on(C, function() {
                        var t = h(this)
                            , i = t.data("settings");
                        "hover" == i.navigation && t.find(".navigator").fadeIn(),
                        "hover" == i.indicator && t.find(".indicator").fadeIn()
                    }).on(b, function() {
                        var t = h(this)
                            , i = t.data("settings");
                        "hover" == i.navigation && t.find(".navigator").fadeOut(),
                        "hover" == i.indicator && t.find(".indicator").fadeOut()
                    }));
                    var d = h("<div />", {
                        class: "controller",
                        html: '<div class="navigator"><span class="prev" data-direction="-1">Previous</span><span class="next" data-direction="1">Next</span></div><div class="indicator"></div>'
                    }).appendTo(this);
                    -1 != navigator.userAgent.toLowerCase().indexOf("msie 8") && d.addClass("msie8");
                    var o = this.find(".navigator")
                        , l = this.find(".indicator");
                    "always" == e.navigation && o.show(),
                        this.find(".navigator span").on("click", function(t) {
                            t.preventDefault(),
                                h(this).getSlider().sliderGo(h(this).data("direction"))
                        }),
                    "always" == e.indicator && l.show();
                    for (var c = 0; c < i; c++)
                        h("<span />", {
                            class: c == s ? "selected" : "",
                            text: c,
                            "data-slide": c
                        }).appendTo(d.find(".indicator")).on("click", function(t) {
                            t.preventDefault();
                            var i = h(this)
                                , a = i.getSlider()
                                , n = a.data("current")
                                , e = i.data("slide");
                            (e < 0 || e >= a.data("total")) && (e = n),
                                a.sliderOptimize(n, e).sliderUpdate(e)
                        });
                    return this.trigger({
                        type: "slideCreated"
                    }),
                        this.sliderResized().sliderUpdate(s).sliderOptimize().sliderPlay(),
                        this
                }
            },
            sliderPlay: function() {
                var t = this.data("current")
                    , i = this.data("map-index")
                    , a = this.data("settings")
                    , n = this.data("total")
                    , e = this.data("cancel-play")
                    , s = this.data("imageLoaded") == this.data("totalImages");
                if (n - 1 <= t)
                    this.sliderStop();
                else if (1 != e && 0 != a.delay) {
                    var r = this;
                    I[i].sliderInterval = setInterval(function() {
                        1 != r.data("cancel-play") ? s && r.sliderGo(1) : clearInterval(I[i].sliderInterval)
                    }, a.delay)
                }
            },
            sliderStop: function() {
                var t = this.data("map-index");
                return clearInterval(I[t].sliderInterval),
                    1 == this.data("cancel-play") ? this : this.data("cancel-play", !0).trigger("slideCancelAutoPlay")
            },
            slideComplete: function() {
                return this.sliderOptimize().trigger({
                    type: "slideComplete",
                    current: this.data("current")
                })
            },
            sliderOptimize: function(t, i) {
                var a = this.data("settings")
                    , n = this.data("current")
                    , e = void 0 !== i ? i : n
                    , s = this.find(".slider-slides > div");
                if ("slide" == a.transition)
                    return this.sliderShowItems();
                for (var r = 0; r < s.length; r++)
                    e != r && s.eq(r).css({
                        "-webkit-transition-duration": 0,
                        "-moz-transition-duration": 0,
                        "-ms-transition-duration": 0,
                        "-o-transition-duration": 0,
                        "transition-duration": 0,
                        "-webkit-opacity": 0,
                        "-moz-opacity": 0,
                        "-ms-opacity": 0,
                        "-o-opacity": 0,
                        opacity: 0,
                        "z-index": 0
                    });
                return this.sliderShowItems()
            },
            sliderCancelTouch: function() {
                return M = k = x = T = 0,
                    z = !1,
                    this
            },
            sliderResized: function() {
                var t, i = this.data("settings"), a = this.data("total"), n = this.data("current"), e = this.width(), s = this.find(".slider-slides > div");
                for (t = 0; t < s.length; t++)
                    "slide" == i.transition ? s.eq(t).css("left", e * t / i.group) : "fade" == i.transition && (s.eq(t).css("z-index", a - t),
                    t == n && s.eq(t).css("z-index", a));
                return "slide" == i.transition && this.find(".slider-slides").translateX(-n * e, !0),
                    this
            },
            sliderGo: function(t) {
                var i = this.data("settings")
                    , a = this.data("current")
                    , n = this.data("total")
                    , e = a + t;
                return (e < 0 || n <= e) && (e = 0 == i.loop ? a : e < 0 ? n - 1 : 0),
                    this.sliderOptimize(a, e).sliderUpdate(e)
            },
            sliderUpdate: function(t) {
                this.data("current") != t && this.trigger({
                    type: "slideStart"
                });
                var i = this.width()
                    , a = this.data("settings");
                return this.data("current", t),
                    "slide" == a.transition ? this.find(".slider-slides").animateX(-t * i, a.speed) : "fade" == a.transition && this.find(".slider-slides > div:eq(" + t + ")").css("z-index", this.data("total")).animateFade(a.speed),
                    this.find(".navigator span").removeClass("disabled"),
                0 == a.loop && (0 == t ? this.find(".navigator .prev").addClass("disabled") : t == this.data("total") - 1 && this.find(".navigator .next").addClass("disabled")),
                    this.find(".indicator span.selected").removeClass("selected"),
                    this.find(".indicator span:eq(" + t + ")").addClass("selected"),
                    this
            },
            sliderShowItems: function() {
                var i = this
                    , t = i.data("current")
                    , a = i.data("map-index")
                    , n = i.find(".slider-slides > div:eq(" + t + ") [class*=appear-]")
                    , e = 0;
                return i.sliderHideItems(),
                    clearInterval(I[a].showInterval),
                n.length <= 0 || (I[a].showInterval = setInterval(function() {
                    if (e >= n.length)
                        return clearInterval(I[a].showInterval),
                            i;
                    var t = n.eq(e);
                    q ? t.css({
                        top: t.data("top"),
                        left: t.data("left"),
                        opacity: 1
                    }) : t.stop(!0, !0).animate({
                        top: t.data("top"),
                        left: t.data("left"),
                        opacity: 1
                    }, 500),
                        e++
                }, 200)),
                    i
            },
            sliderHideItems: function() {
                var t = this.data("current")
                    , i = this.data("map-index");
                return clearInterval(I[i].showInterval),
                    this.find(".slider-slides > div:not(:eq(" + t + ")) [class*=appear-]").each(function() {
                        var t = h(this)
                            , i = t.data("top")
                            , a = t.data("left");
                        t.hasClass("appear-top") && t.css("top", i - 15),
                        t.hasClass("appear-bottom") && t.css("top", i + 15),
                        t.hasClass("appear-left") && t.css("left", i - 15),
                        t.hasClass("appear-right") && t.css("left", a + 15),
                            t.css("opacity", 0)
                    }),
                    this
            },
            getSlider: function() {
                return this.hasClass("jSlider") ? this : this.parents(".jSlider")
            }
        }),
        h(document).ready(function(t) {
            h("body .jSlider").each(function() {
                h(this).sliderInit()
            })
        }),
        h(window).resize(function(t) {
            h("body .jSlider").each(function() {
                h(this).sliderResized()
            })
        })
});
//# sourceMappingURL=jquery.jSlider.min.js.map
