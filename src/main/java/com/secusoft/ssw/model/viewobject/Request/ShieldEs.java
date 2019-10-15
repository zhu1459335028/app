package com.secusoft.ssw.model.viewobject.Request;

public class ShieldEs {


    private String id;


    private Integer status; //0已连接，1未连接 2连接失败

    private String avgDaily_db47_52;//水平位移平均值

    private String avgDaily_db47_56;//垂直位移平均值

    private Integer levelstatus ;//水平报警状态 0 不报警  1 报警

    private String levelcoler;//水平位移报警颜色

    public String getLevelcoler() {
        return levelcoler;
    }

    public void setLevelcoler(String levelcoler) {
        this.levelcoler = levelcoler;
    }

    public String getVerticalcoler() {
        return verticalcoler;
    }

    public void setVerticalcoler(String verticalcoler) {
        this.verticalcoler = verticalcoler;
    }

    private String verticalcoler;//垂直位移报警颜色



    public Integer getLevelstatus() {
        return levelstatus;
    }

    public void setLevelstatus(Integer levelstatus) {
        this.levelstatus = levelstatus;
    }

    public Integer getVerticalstatus() {
        return verticalstatus;
    }

    public void setVerticalstatus(Integer verticalstatus) {
        this.verticalstatus = verticalstatus;
    }

    private Integer verticalstatus ; //垂直报警状态 0 不报警  1 报警

    public String getAvgDaily_db47_52() {
        return avgDaily_db47_52;
    }

    public void setAvgDaily_db47_52(String avgDaily_db47_52) {
        this.avgDaily_db47_52 = avgDaily_db47_52;
    }

    public String getAvgDaily_db47_56() {
        return avgDaily_db47_56;
    }

    public void setAvgDaily_db47_56(String avgDaily_db47_56) {
        this.avgDaily_db47_56 = avgDaily_db47_56;
    }

    private String db40_344;//沼气

    private String db40_1240;//氧气

    private String db40_296;//硫化氢


    private String db40_128;


    private String db47_60;


    private String shieldno;


    private String datetime;


    private Integer outletid;


    private String db49_404;


    private String m58_4;


    private String m58_3;


    private String db40_512;


    private String db40_516;


    private String db40_520;


    private String db40_524;


    private String db40_44;


    private String db40_52;


    private String db40_60;


    private String db40_68;


    private String db40_140;


    private String db40_144;


    private String db40_156;

    private String db40_152;

    private String db40_148;

    private String db40_778;

    private String db40_124;

    private String db40_1084;

    private String db40_32;

    private String db40_320;

    private String db40_552;

    private String db40_1018;

    /**
     * 抓举头压力 db40_1286
     *
     * 螺机后部压力 db40_1286;
     */

    private String db40_1286;

    private String db40_72;

    private String db40_772;

    private String db40_1544;

    private String db40_668;

    private String db40_676;

    private String db40_20;

    private String db40_336;

    private String db40_24;

    private String db40_794;

    private String db40_802;

    private String db40_1250;

    private String db40_116;

    private String db40_340;

    private String db40_120;

    private String db40_992;

    private String db40_260;

    private String db40_544;

    private String db40_548;
    /**
     * 铰接泵压力 db40_1380
     * 铰接手动控制泵出口压力 db40_1380
     */

    private String db40_1380;

    private String db40_528;

    private String db40_532;

    private String db40_540;

    private String db40_536;

    private String db40_160;

    private String db40_308;

    private String db40_256;

    private String db40_276;

    private String db40_1232;

    private String db40_374;

    private String db40_1228;

    private String db40_372;

    private String db40_420;

    private String db40_1396;

    private String db40_1388;

    private String db40_1392;

    private String db40_1270;

    private String db40_1258;

    private String db40_1266;

    private String db40_1262;

    private String db40_188;

    private String db40_352;

    private String db40_902;

    private String db40_728;

    private String db40_192;

    private String db40_744;

    private String db40_208;

    private String db40_224;

    private String db40_732;

    private String db40_196;

    private String db40_748;

    private String db40_212;

    private String db40_228;

    private String db40_736;

    private String db40_200;

    private String db40_752;

    private String db40_216;

    private String db40_232;

    private String db40_928;

    private String db40_854;

    private String db40_952;

    private String db40_878;

    private String db40_236;

    private String db40_932;

    private String db40_858;

    private String db40_956;

    private String db40_882;

    private String db40_906;

    private String db40_936;

    private String db40_862;

    private String db40_960;

    private String db40_886;

    private String db40_910;

    private String db43_72;

    private String db43_148;

    private String db43_180;

    private String db43_196;

    private String db43_184;

    private String db43_200;

    private String db43_188;

    private String db43_204;

    private String db43_520;

    private String db43_544;

    private String db43_524;

    private String db43_548;

    private String db43_528;

    private String db43_552;

    private String db43_256;

    private String db43_260;

    private String db43_494;

    /**
     *注浆压力左上 注浆控制左上注浆压力
     *     private String db40_168;
     */

    private String db40_168;

    private String db43_502;

    /**
     * 注浆控制右上注浆压力 db40_172
     * 注浆压力右上 db40_172
     */

    private String db40_172;

    private String db43_506;
    /**
     *
     注浆控制右下注浆压力db40_176
     注浆压力右下db40_176
     */

    private String db40_176;

    private String db43_498;
    /**
     *注浆控制左下注浆压力 db40_180
     * 注浆压力左下 db40_180
     */

    private String db40_180;

    private String db40_620;

    private String db40_624;

    private String db40_628;

    private String db40_632;

    private String db40_636;

    private String db40_640;

    private String db40_1174;

    private String db40_644;

    private String db40_648;

    private String db40_652;

    private String db40_656;

    private String db40_660;

    private String db40_664;

    private String db40_1178;

    private String db40_4;

    private String db40_572;

    private String db40_596;

    private String db40_576;

    private String db40_600;

    private String db40_580;

    private String db40_604;

    private String db40_584;

    private String db40_608;

    private String db40_588;

    private String db40_612;

    private String db40_592;

    private String db40_616;

    private String db40_1166;

    private String db40_1170;

    private String db90_432;

    private String db90_400;

    private String db90_408;

    private String db90_404;

    private String db90_412;

    private String db90_416;

    private String db90_420;

    private String db90_32;

    private String db90_0;

    private String db90_8;

    private String db90_4;

    private String db90_12;

    private String db90_16;

    private String db90_20;

    /**
     *刀盘监测超挖量 db40_798
     * 超挖刀超挖量测量 db40_798
     */

    private String db40_798;

    private String db43_486;

    private String db43_464;

    private String db43_468;

    private String db43_602;

    private String db40_1096;

    private String db40_1100;

    private String db40_1104;

    private String db40_1108;

    private String db40_1112;

    private String db40_458;

    private String db40_464;

    private String db40_460;

    private String db40_466;


    private String db40_8;

    private String db40_12;

    private String db40_164;

    private String db40_1274;

    private String db40_1278;

    private String db40_1282;

    private String db40_1290;

    private String db40_1294;

    private String db43_628;

    private String db40_1354;

    private String db40_1358;

    private String db40_1330;

    private String db40_1334;

    private String db40_1338;

    private String db40_1342;

    private String db40_1346;

    private String db40_1350;

    private String db40_1322;

    private String db40_1326;

    private String db40_1548;

    private String db40_240;

    private String db40_80;

    private String db40_184;

    private String db40_84;

    private String db40_92;

    private String db40_88;

    private String db40_1556;

    private String db40_1552;

    private String db40_248;

    private String db40_96;

    private String db40_244;

    private String db40_100;

    private String db40_108;

    private String db40_104;

    private String db40_1560;

    private String db49_80;

    private String db49_84;

    private String db49_88;

    private String db49_92;

    private String db49_96;

    private String db49_100;

    private String db49_204;

    private String db49_208;

    private String db49_212;

    private String db49_216;

    private String db49_280;

    private String db49_284;

    private String db49_288;

    private String db49_292;

    private String db49_296;

    private String db49_300;

    private String db49_180;

    private String db49_184;

    private String db49_116;

    private String db49_120;

    private String db49_156;

    private String db49_160;

    private String db49_136;

    private String db49_124;

    private String db49_132;

    private String db49_128;

    private String db49_240;

    private String db49_244;

    private String db49_328;

    private String db49_332;

    private String db47_4;

    private String db47_0;

    private String db47_52;

    private String db47_76;
    /**
     *VMT导向水平后 db47_64
     * VMT导向垂直后 db47_64
     */

    private String db47_64;

    private String db47_56;

    private String db47_80;

    private String db47_16;

    private String db47_256;

    private String db46_36;

    private String db46_40;

    private String db46_44;

    private String db46_48;

    public String getDb40_128() {
        return db40_128;
    }

    public void setDb40_128(String db40_128) {
        this.db40_128 = db40_128;
    }

    public String getDb47_60() {
        return db47_60;
    }

    public void setDb47_60(String db47_60) {
        this.db47_60 = db47_60;
    }

    public String getShieldno() {
        return shieldno;
    }

    public void setShieldno(String shieldno) {
        this.shieldno = shieldno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDb49_404() {
        return db49_404;
    }

    public void setDb49_404(String db49_404) {
        this.db49_404 = db49_404;
    }

    public String getM58_4() {
        return m58_4;
    }

    public void setM58_4(String m58_4) {
        this.m58_4 = m58_4;
    }

    public String getM58_3() {
        return m58_3;
    }

    public void setM58_3(String m58_3) {
        this.m58_3 = m58_3;
    }

    public String getDb40_512() {
        return db40_512;
    }

    public void setDb40_512(String db40_512) {
        this.db40_512 = db40_512;
    }

    public String getDb40_516() {
        return db40_516;
    }

    public void setDb40_516(String db40_516) {
        this.db40_516 = db40_516;
    }

    public String getDb40_520() {
        return db40_520;
    }

    public void setDb40_520(String db40_520) {
        this.db40_520 = db40_520;
    }

    public String getDb40_524() {
        return db40_524;
    }

    public void setDb40_524(String db40_524) {
        this.db40_524 = db40_524;
    }

    public String getDb40_44() {
        return db40_44;
    }

    public void setDb40_44(String db40_44) {
        this.db40_44 = db40_44;
    }

    public String getDb40_52() {
        return db40_52;
    }

    public void setDb40_52(String db40_52) {
        this.db40_52 = db40_52;
    }

    public String getDb40_60() {
        return db40_60;
    }

    public void setDb40_60(String db40_60) {
        this.db40_60 = db40_60;
    }

    public String getDb40_68() {
        return db40_68;
    }

    public void setDb40_68(String db40_68) {
        this.db40_68 = db40_68;
    }

    public String getDb40_140() {
        return db40_140;
    }

    public void setDb40_140(String db40_140) {
        this.db40_140 = db40_140;
    }

    public String getDb40_144() {
        return db40_144;
    }

    public void setDb40_144(String db40_144) {
        this.db40_144 = db40_144;
    }

    public String getDb40_156() {
        return db40_156;
    }

    public void setDb40_156(String db40_156) {
        this.db40_156 = db40_156;
    }

    public String getDb40_152() {
        return db40_152;
    }

    public void setDb40_152(String db40_152) {
        this.db40_152 = db40_152;
    }

    public String getDb40_148() {
        return db40_148;
    }

    public void setDb40_148(String db40_148) {
        this.db40_148 = db40_148;
    }

    public String getDb40_778() {
        return db40_778;
    }

    public void setDb40_778(String db40_778) {
        this.db40_778 = db40_778;
    }

    public String getDb40_124() {
        return db40_124;
    }

    public void setDb40_124(String db40_124) {
        this.db40_124 = db40_124;
    }

    public String getDb40_1084() {
        return db40_1084;
    }

    public void setDb40_1084(String db40_1084) {
        this.db40_1084 = db40_1084;
    }

    public String getDb40_32() {
        return db40_32;
    }

    public void setDb40_32(String db40_32) {
        this.db40_32 = db40_32;
    }

    public String getDb40_320() {
        return db40_320;
    }

    public void setDb40_320(String db40_320) {
        this.db40_320 = db40_320;
    }

    public String getDb40_552() {
        return db40_552;
    }

    public void setDb40_552(String db40_552) {
        this.db40_552 = db40_552;
    }

    public String getDb40_1018() {
        return db40_1018;
    }

    public void setDb40_1018(String db40_1018) {
        this.db40_1018 = db40_1018;
    }

    public String getDb40_1286() {
        return db40_1286;
    }

    public void setDb40_1286(String db40_1286) {
        this.db40_1286 = db40_1286;
    }

    public String getDb40_72() {
        return db40_72;
    }

    public void setDb40_72(String db40_72) {
        this.db40_72 = db40_72;
    }

    public String getDb40_772() {
        return db40_772;
    }

    public void setDb40_772(String db40_772) {
        this.db40_772 = db40_772;
    }

    public String getDb40_1544() {
        return db40_1544;
    }

    public void setDb40_1544(String db40_1544) {
        this.db40_1544 = db40_1544;
    }

    public String getDb40_668() {
        return db40_668;
    }

    public void setDb40_668(String db40_668) {
        this.db40_668 = db40_668;
    }

    public String getDb40_676() {
        return db40_676;
    }

    public void setDb40_676(String db40_676) {
        this.db40_676 = db40_676;
    }

    public String getDb40_20() {
        return db40_20;
    }

    public void setDb40_20(String db40_20) {
        this.db40_20 = db40_20;
    }

    public String getDb40_336() {
        return db40_336;
    }

    public void setDb40_336(String db40_336) {
        this.db40_336 = db40_336;
    }

    public String getDb40_24() {
        return db40_24;
    }

    public void setDb40_24(String db40_24) {
        this.db40_24 = db40_24;
    }

    public String getDb40_794() {
        return db40_794;
    }

    public void setDb40_794(String db40_794) {
        this.db40_794 = db40_794;
    }

    public String getDb40_802() {
        return db40_802;
    }

    public void setDb40_802(String db40_802) {
        this.db40_802 = db40_802;
    }

    public String getDb40_1250() {
        return db40_1250;
    }

    public void setDb40_1250(String db40_1250) {
        this.db40_1250 = db40_1250;
    }

    public String getDb40_116() {
        return db40_116;
    }

    public void setDb40_116(String db40_116) {
        this.db40_116 = db40_116;
    }

    public String getDb40_340() {
        return db40_340;
    }

    public void setDb40_340(String db40_340) {
        this.db40_340 = db40_340;
    }

    public String getDb40_120() {
        return db40_120;
    }

    public void setDb40_120(String db40_120) {
        this.db40_120 = db40_120;
    }

    public String getDb40_992() {
        return db40_992;
    }

    public void setDb40_992(String db40_992) {
        this.db40_992 = db40_992;
    }

    public String getDb40_260() {
        return db40_260;
    }

    public void setDb40_260(String db40_260) {
        this.db40_260 = db40_260;
    }

    public String getDb40_544() {
        return db40_544;
    }

    public void setDb40_544(String db40_544) {
        this.db40_544 = db40_544;
    }

    public String getDb40_548() {
        return db40_548;
    }

    public void setDb40_548(String db40_548) {
        this.db40_548 = db40_548;
    }

    public String getDb40_1380() {
        return db40_1380;
    }

    public void setDb40_1380(String db40_1380) {
        this.db40_1380 = db40_1380;
    }

    public String getDb40_528() {
        return db40_528;
    }

    public void setDb40_528(String db40_528) {
        this.db40_528 = db40_528;
    }

    public String getDb40_532() {
        return db40_532;
    }

    public void setDb40_532(String db40_532) {
        this.db40_532 = db40_532;
    }

    public String getDb40_540() {
        return db40_540;
    }

    public void setDb40_540(String db40_540) {
        this.db40_540 = db40_540;
    }

    public String getDb40_536() {
        return db40_536;
    }

    public void setDb40_536(String db40_536) {
        this.db40_536 = db40_536;
    }

    public String getDb40_160() {
        return db40_160;
    }

    public void setDb40_160(String db40_160) {
        this.db40_160 = db40_160;
    }

    public String getDb40_308() {
        return db40_308;
    }

    public void setDb40_308(String db40_308) {
        this.db40_308 = db40_308;
    }

    public String getDb40_256() {
        return db40_256;
    }

    public void setDb40_256(String db40_256) {
        this.db40_256 = db40_256;
    }

    public String getDb40_276() {
        return db40_276;
    }

    public void setDb40_276(String db40_276) {
        this.db40_276 = db40_276;
    }

    public String getDb40_1232() {
        return db40_1232;
    }

    public void setDb40_1232(String db40_1232) {
        this.db40_1232 = db40_1232;
    }

    public String getDb40_374() {
        return db40_374;
    }

    public void setDb40_374(String db40_374) {
        this.db40_374 = db40_374;
    }

    public String getDb40_1228() {
        return db40_1228;
    }

    public void setDb40_1228(String db40_1228) {
        this.db40_1228 = db40_1228;
    }

    public String getDb40_372() {
        return db40_372;
    }

    public void setDb40_372(String db40_372) {
        this.db40_372 = db40_372;
    }

    public String getDb40_420() {
        return db40_420;
    }

    public void setDb40_420(String db40_420) {
        this.db40_420 = db40_420;
    }

    public String getDb40_1396() {
        return db40_1396;
    }

    public void setDb40_1396(String db40_1396) {
        this.db40_1396 = db40_1396;
    }

    public String getDb40_1388() {
        return db40_1388;
    }

    public void setDb40_1388(String db40_1388) {
        this.db40_1388 = db40_1388;
    }

    public String getDb40_1392() {
        return db40_1392;
    }

    public void setDb40_1392(String db40_1392) {
        this.db40_1392 = db40_1392;
    }

    public String getDb40_1270() {
        return db40_1270;
    }

    public void setDb40_1270(String db40_1270) {
        this.db40_1270 = db40_1270;
    }

    public String getDb40_1258() {
        return db40_1258;
    }

    public void setDb40_1258(String db40_1258) {
        this.db40_1258 = db40_1258;
    }

    public String getDb40_1266() {
        return db40_1266;
    }

    public void setDb40_1266(String db40_1266) {
        this.db40_1266 = db40_1266;
    }

    public String getDb40_1262() {
        return db40_1262;
    }

    public void setDb40_1262(String db40_1262) {
        this.db40_1262 = db40_1262;
    }

    public String getDb40_188() {
        return db40_188;
    }

    public void setDb40_188(String db40_188) {
        this.db40_188 = db40_188;
    }

    public String getDb40_352() {
        return db40_352;
    }

    public void setDb40_352(String db40_352) {
        this.db40_352 = db40_352;
    }

    public String getDb40_902() {
        return db40_902;
    }

    public void setDb40_902(String db40_902) {
        this.db40_902 = db40_902;
    }

    public String getDb40_728() {
        return db40_728;
    }

    public void setDb40_728(String db40_728) {
        this.db40_728 = db40_728;
    }

    public String getDb40_192() {
        return db40_192;
    }

    public void setDb40_192(String db40_192) {
        this.db40_192 = db40_192;
    }

    public String getDb40_744() {
        return db40_744;
    }

    public void setDb40_744(String db40_744) {
        this.db40_744 = db40_744;
    }

    public String getDb40_208() {
        return db40_208;
    }

    public void setDb40_208(String db40_208) {
        this.db40_208 = db40_208;
    }

    public String getDb40_224() {
        return db40_224;
    }

    public void setDb40_224(String db40_224) {
        this.db40_224 = db40_224;
    }

    public String getDb40_732() {
        return db40_732;
    }

    public void setDb40_732(String db40_732) {
        this.db40_732 = db40_732;
    }

    public String getDb40_196() {
        return db40_196;
    }

    public void setDb40_196(String db40_196) {
        this.db40_196 = db40_196;
    }

    public String getDb40_748() {
        return db40_748;
    }

    public void setDb40_748(String db40_748) {
        this.db40_748 = db40_748;
    }

    public String getDb40_212() {
        return db40_212;
    }

    public void setDb40_212(String db40_212) {
        this.db40_212 = db40_212;
    }

    public String getDb40_228() {
        return db40_228;
    }

    public void setDb40_228(String db40_228) {
        this.db40_228 = db40_228;
    }

    public String getDb40_736() {
        return db40_736;
    }

    public void setDb40_736(String db40_736) {
        this.db40_736 = db40_736;
    }

    public String getDb40_200() {
        return db40_200;
    }

    public void setDb40_200(String db40_200) {
        this.db40_200 = db40_200;
    }

    public String getDb40_752() {
        return db40_752;
    }

    public void setDb40_752(String db40_752) {
        this.db40_752 = db40_752;
    }

    public String getDb40_216() {
        return db40_216;
    }

    public void setDb40_216(String db40_216) {
        this.db40_216 = db40_216;
    }

    public String getDb40_232() {
        return db40_232;
    }

    public void setDb40_232(String db40_232) {
        this.db40_232 = db40_232;
    }

    public String getDb40_928() {
        return db40_928;
    }

    public void setDb40_928(String db40_928) {
        this.db40_928 = db40_928;
    }

    public String getDb40_854() {
        return db40_854;
    }

    public void setDb40_854(String db40_854) {
        this.db40_854 = db40_854;
    }

    public String getDb40_952() {
        return db40_952;
    }

    public void setDb40_952(String db40_952) {
        this.db40_952 = db40_952;
    }

    public String getDb40_878() {
        return db40_878;
    }

    public void setDb40_878(String db40_878) {
        this.db40_878 = db40_878;
    }

    public String getDb40_236() {
        return db40_236;
    }

    public void setDb40_236(String db40_236) {
        this.db40_236 = db40_236;
    }

    public String getDb40_932() {
        return db40_932;
    }

    public void setDb40_932(String db40_932) {
        this.db40_932 = db40_932;
    }

    public String getDb40_858() {
        return db40_858;
    }

    public void setDb40_858(String db40_858) {
        this.db40_858 = db40_858;
    }

    public String getDb40_956() {
        return db40_956;
    }

    public void setDb40_956(String db40_956) {
        this.db40_956 = db40_956;
    }

    public String getDb40_882() {
        return db40_882;
    }

    public void setDb40_882(String db40_882) {
        this.db40_882 = db40_882;
    }

    public String getDb40_906() {
        return db40_906;
    }

    public void setDb40_906(String db40_906) {
        this.db40_906 = db40_906;
    }

    public String getDb40_936() {
        return db40_936;
    }

    public void setDb40_936(String db40_936) {
        this.db40_936 = db40_936;
    }

    public String getDb40_862() {
        return db40_862;
    }

    public void setDb40_862(String db40_862) {
        this.db40_862 = db40_862;
    }

    public String getDb40_960() {
        return db40_960;
    }

    public void setDb40_960(String db40_960) {
        this.db40_960 = db40_960;
    }

    public String getDb40_886() {
        return db40_886;
    }

    public void setDb40_886(String db40_886) {
        this.db40_886 = db40_886;
    }

    public String getDb40_910() {
        return db40_910;
    }

    public void setDb40_910(String db40_910) {
        this.db40_910 = db40_910;
    }

    public String getDb43_72() {
        return db43_72;
    }

    public void setDb43_72(String db43_72) {
        this.db43_72 = db43_72;
    }

    public String getDb43_148() {
        return db43_148;
    }

    public void setDb43_148(String db43_148) {
        this.db43_148 = db43_148;
    }

    public String getDb43_180() {
        return db43_180;
    }

    public void setDb43_180(String db43_180) {
        this.db43_180 = db43_180;
    }

    public String getDb43_196() {
        return db43_196;
    }

    public void setDb43_196(String db43_196) {
        this.db43_196 = db43_196;
    }

    public String getDb43_184() {
        return db43_184;
    }

    public void setDb43_184(String db43_184) {
        this.db43_184 = db43_184;
    }

    public String getDb43_200() {
        return db43_200;
    }

    public void setDb43_200(String db43_200) {
        this.db43_200 = db43_200;
    }

    public String getDb43_188() {
        return db43_188;
    }

    public void setDb43_188(String db43_188) {
        this.db43_188 = db43_188;
    }

    public String getDb43_204() {
        return db43_204;
    }

    public void setDb43_204(String db43_204) {
        this.db43_204 = db43_204;
    }

    public String getDb43_520() {
        return db43_520;
    }

    public void setDb43_520(String db43_520) {
        this.db43_520 = db43_520;
    }

    public String getDb43_544() {
        return db43_544;
    }

    public void setDb43_544(String db43_544) {
        this.db43_544 = db43_544;
    }

    public String getDb43_524() {
        return db43_524;
    }

    public void setDb43_524(String db43_524) {
        this.db43_524 = db43_524;
    }

    public String getDb43_548() {
        return db43_548;
    }

    public void setDb43_548(String db43_548) {
        this.db43_548 = db43_548;
    }

    public String getDb43_528() {
        return db43_528;
    }

    public void setDb43_528(String db43_528) {
        this.db43_528 = db43_528;
    }

    public String getDb43_552() {
        return db43_552;
    }

    public void setDb43_552(String db43_552) {
        this.db43_552 = db43_552;
    }

    public String getDb43_256() {
        return db43_256;
    }

    public void setDb43_256(String db43_256) {
        this.db43_256 = db43_256;
    }

    public String getDb43_260() {
        return db43_260;
    }

    public void setDb43_260(String db43_260) {
        this.db43_260 = db43_260;
    }

    public String getDb43_494() {
        return db43_494;
    }

    public void setDb43_494(String db43_494) {
        this.db43_494 = db43_494;
    }

    public String getDb40_168() {
        return db40_168;
    }

    public void setDb40_168(String db40_168) {
        this.db40_168 = db40_168;
    }

    public String getDb43_502() {
        return db43_502;
    }

    public void setDb43_502(String db43_502) {
        this.db43_502 = db43_502;
    }

    public String getDb40_172() {
        return db40_172;
    }

    public void setDb40_172(String db40_172) {
        this.db40_172 = db40_172;
    }

    public String getDb43_506() {
        return db43_506;
    }

    public void setDb43_506(String db43_506) {
        this.db43_506 = db43_506;
    }

    public String getDb40_176() {
        return db40_176;
    }

    public void setDb40_176(String db40_176) {
        this.db40_176 = db40_176;
    }

    public String getDb43_498() {
        return db43_498;
    }

    public void setDb43_498(String db43_498) {
        this.db43_498 = db43_498;
    }

    public String getDb40_180() {
        return db40_180;
    }

    public void setDb40_180(String db40_180) {
        this.db40_180 = db40_180;
    }

    public String getDb40_620() {
        return db40_620;
    }

    public void setDb40_620(String db40_620) {
        this.db40_620 = db40_620;
    }

    public String getDb40_624() {
        return db40_624;
    }

    public void setDb40_624(String db40_624) {
        this.db40_624 = db40_624;
    }

    public String getDb40_628() {
        return db40_628;
    }

    public void setDb40_628(String db40_628) {
        this.db40_628 = db40_628;
    }

    public String getDb40_632() {
        return db40_632;
    }

    public void setDb40_632(String db40_632) {
        this.db40_632 = db40_632;
    }

    public String getDb40_636() {
        return db40_636;
    }

    public void setDb40_636(String db40_636) {
        this.db40_636 = db40_636;
    }

    public String getDb40_640() {
        return db40_640;
    }

    public void setDb40_640(String db40_640) {
        this.db40_640 = db40_640;
    }

    public String getDb40_1174() {
        return db40_1174;
    }

    public void setDb40_1174(String db40_1174) {
        this.db40_1174 = db40_1174;
    }

    public String getDb40_644() {
        return db40_644;
    }

    public void setDb40_644(String db40_644) {
        this.db40_644 = db40_644;
    }

    public String getDb40_648() {
        return db40_648;
    }

    public void setDb40_648(String db40_648) {
        this.db40_648 = db40_648;
    }

    public String getDb40_652() {
        return db40_652;
    }

    public void setDb40_652(String db40_652) {
        this.db40_652 = db40_652;
    }

    public String getDb40_656() {
        return db40_656;
    }

    public void setDb40_656(String db40_656) {
        this.db40_656 = db40_656;
    }

    public String getDb40_660() {
        return db40_660;
    }

    public void setDb40_660(String db40_660) {
        this.db40_660 = db40_660;
    }

    public String getDb40_664() {
        return db40_664;
    }

    public void setDb40_664(String db40_664) {
        this.db40_664 = db40_664;
    }

    public String getDb40_1178() {
        return db40_1178;
    }

    public void setDb40_1178(String db40_1178) {
        this.db40_1178 = db40_1178;
    }

    public String getDb40_4() {
        return db40_4;
    }

    public void setDb40_4(String db40_4) {
        this.db40_4 = db40_4;
    }

    public String getDb40_572() {
        return db40_572;
    }

    public void setDb40_572(String db40_572) {
        this.db40_572 = db40_572;
    }

    public String getDb40_596() {
        return db40_596;
    }

    public void setDb40_596(String db40_596) {
        this.db40_596 = db40_596;
    }

    public String getDb40_576() {
        return db40_576;
    }

    public void setDb40_576(String db40_576) {
        this.db40_576 = db40_576;
    }

    public String getDb40_600() {
        return db40_600;
    }

    public void setDb40_600(String db40_600) {
        this.db40_600 = db40_600;
    }

    public String getDb40_580() {
        return db40_580;
    }

    public void setDb40_580(String db40_580) {
        this.db40_580 = db40_580;
    }

    public String getDb40_604() {
        return db40_604;
    }

    public void setDb40_604(String db40_604) {
        this.db40_604 = db40_604;
    }

    public String getDb40_584() {
        return db40_584;
    }

    public void setDb40_584(String db40_584) {
        this.db40_584 = db40_584;
    }

    public String getDb40_608() {
        return db40_608;
    }

    public void setDb40_608(String db40_608) {
        this.db40_608 = db40_608;
    }

    public String getDb40_588() {
        return db40_588;
    }

    public void setDb40_588(String db40_588) {
        this.db40_588 = db40_588;
    }

    public String getDb40_612() {
        return db40_612;
    }

    public void setDb40_612(String db40_612) {
        this.db40_612 = db40_612;
    }

    public String getDb40_592() {
        return db40_592;
    }

    public void setDb40_592(String db40_592) {
        this.db40_592 = db40_592;
    }

    public String getDb40_616() {
        return db40_616;
    }

    public void setDb40_616(String db40_616) {
        this.db40_616 = db40_616;
    }

    public String getDb40_1166() {
        return db40_1166;
    }

    public void setDb40_1166(String db40_1166) {
        this.db40_1166 = db40_1166;
    }

    public String getDb40_1170() {
        return db40_1170;
    }

    public void setDb40_1170(String db40_1170) {
        this.db40_1170 = db40_1170;
    }

    public String getDb90_432() {
        return db90_432;
    }

    public void setDb90_432(String db90_432) {
        this.db90_432 = db90_432;
    }

    public String getDb90_400() {
        return db90_400;
    }

    public void setDb90_400(String db90_400) {
        this.db90_400 = db90_400;
    }

    public String getDb90_408() {
        return db90_408;
    }

    public void setDb90_408(String db90_408) {
        this.db90_408 = db90_408;
    }

    public String getDb90_404() {
        return db90_404;
    }

    public void setDb90_404(String db90_404) {
        this.db90_404 = db90_404;
    }

    public String getDb90_412() {
        return db90_412;
    }

    public void setDb90_412(String db90_412) {
        this.db90_412 = db90_412;
    }

    public String getDb90_416() {
        return db90_416;
    }

    public void setDb90_416(String db90_416) {
        this.db90_416 = db90_416;
    }

    public String getDb90_420() {
        return db90_420;
    }

    public void setDb90_420(String db90_420) {
        this.db90_420 = db90_420;
    }

    public String getDb90_32() {
        return db90_32;
    }

    public void setDb90_32(String db90_32) {
        this.db90_32 = db90_32;
    }

    public String getDb90_0() {
        return db90_0;
    }

    public void setDb90_0(String db90_0) {
        this.db90_0 = db90_0;
    }

    public String getDb90_8() {
        return db90_8;
    }

    public void setDb90_8(String db90_8) {
        this.db90_8 = db90_8;
    }

    public String getDb90_4() {
        return db90_4;
    }

    public void setDb90_4(String db90_4) {
        this.db90_4 = db90_4;
    }

    public String getDb90_12() {
        return db90_12;
    }

    public void setDb90_12(String db90_12) {
        this.db90_12 = db90_12;
    }

    public String getDb90_16() {
        return db90_16;
    }

    public void setDb90_16(String db90_16) {
        this.db90_16 = db90_16;
    }

    public String getDb90_20() {
        return db90_20;
    }

    public void setDb90_20(String db90_20) {
        this.db90_20 = db90_20;
    }

    public String getDb40_798() {
        return db40_798;
    }

    public void setDb40_798(String db40_798) {
        this.db40_798 = db40_798;
    }

    public String getDb43_486() {
        return db43_486;
    }

    public void setDb43_486(String db43_486) {
        this.db43_486 = db43_486;
    }

    public String getDb43_464() {
        return db43_464;
    }

    public void setDb43_464(String db43_464) {
        this.db43_464 = db43_464;
    }

    public String getDb43_468() {
        return db43_468;
    }

    public void setDb43_468(String db43_468) {
        this.db43_468 = db43_468;
    }

    public String getDb43_602() {
        return db43_602;
    }

    public void setDb43_602(String db43_602) {
        this.db43_602 = db43_602;
    }

    public String getDb40_1096() {
        return db40_1096;
    }

    public void setDb40_1096(String db40_1096) {
        this.db40_1096 = db40_1096;
    }

    public String getDb40_1100() {
        return db40_1100;
    }

    public void setDb40_1100(String db40_1100) {
        this.db40_1100 = db40_1100;
    }

    public String getDb40_1104() {
        return db40_1104;
    }

    public void setDb40_1104(String db40_1104) {
        this.db40_1104 = db40_1104;
    }

    public String getDb40_1108() {
        return db40_1108;
    }

    public void setDb40_1108(String db40_1108) {
        this.db40_1108 = db40_1108;
    }

    public String getDb40_1112() {
        return db40_1112;
    }

    public void setDb40_1112(String db40_1112) {
        this.db40_1112 = db40_1112;
    }

    public String getDb40_458() {
        return db40_458;
    }

    public void setDb40_458(String db40_458) {
        this.db40_458 = db40_458;
    }

    public String getDb40_464() {
        return db40_464;
    }

    public void setDb40_464(String db40_464) {
        this.db40_464 = db40_464;
    }

    public String getDb40_460() {
        return db40_460;
    }

    public void setDb40_460(String db40_460) {
        this.db40_460 = db40_460;
    }

    public String getDb40_466() {
        return db40_466;
    }

    public void setDb40_466(String db40_466) {
        this.db40_466 = db40_466;
    }

    public String getDb40_344() {
        return db40_344;
    }

    public void setDb40_344(String db40_344) {
        this.db40_344 = db40_344;
    }

    public String getDb40_1240() {
        return db40_1240;
    }

    public void setDb40_1240(String db40_1240) {
        this.db40_1240 = db40_1240;
    }

    public String getDb40_296() {
        return db40_296;
    }

    public void setDb40_296(String db40_296) {
        this.db40_296 = db40_296;
    }

    public String getDb40_8() {
        return db40_8;
    }

    public void setDb40_8(String db40_8) {
        this.db40_8 = db40_8;
    }

    public String getDb40_12() {
        return db40_12;
    }

    public void setDb40_12(String db40_12) {
        this.db40_12 = db40_12;
    }

    public String getDb40_164() {
        return db40_164;
    }

    public void setDb40_164(String db40_164) {
        this.db40_164 = db40_164;
    }

    public String getDb40_1274() {
        return db40_1274;
    }

    public void setDb40_1274(String db40_1274) {
        this.db40_1274 = db40_1274;
    }

    public String getDb40_1278() {
        return db40_1278;
    }

    public void setDb40_1278(String db40_1278) {
        this.db40_1278 = db40_1278;
    }

    public String getDb40_1282() {
        return db40_1282;
    }

    public void setDb40_1282(String db40_1282) {
        this.db40_1282 = db40_1282;
    }

    public String getDb40_1290() {
        return db40_1290;
    }

    public void setDb40_1290(String db40_1290) {
        this.db40_1290 = db40_1290;
    }

    public String getDb40_1294() {
        return db40_1294;
    }

    public void setDb40_1294(String db40_1294) {
        this.db40_1294 = db40_1294;
    }

    public String getDb43_628() {
        return db43_628;
    }

    public void setDb43_628(String db43_628) {
        this.db43_628 = db43_628;
    }

    public String getDb40_1354() {
        return db40_1354;
    }

    public void setDb40_1354(String db40_1354) {
        this.db40_1354 = db40_1354;
    }

    public String getDb40_1358() {
        return db40_1358;
    }

    public void setDb40_1358(String db40_1358) {
        this.db40_1358 = db40_1358;
    }

    public String getDb40_1330() {
        return db40_1330;
    }

    public void setDb40_1330(String db40_1330) {
        this.db40_1330 = db40_1330;
    }

    public String getDb40_1334() {
        return db40_1334;
    }

    public void setDb40_1334(String db40_1334) {
        this.db40_1334 = db40_1334;
    }

    public String getDb40_1338() {
        return db40_1338;
    }

    public void setDb40_1338(String db40_1338) {
        this.db40_1338 = db40_1338;
    }

    public String getDb40_1342() {
        return db40_1342;
    }

    public void setDb40_1342(String db40_1342) {
        this.db40_1342 = db40_1342;
    }

    public String getDb40_1346() {
        return db40_1346;
    }

    public void setDb40_1346(String db40_1346) {
        this.db40_1346 = db40_1346;
    }

    public String getDb40_1350() {
        return db40_1350;
    }

    public void setDb40_1350(String db40_1350) {
        this.db40_1350 = db40_1350;
    }

    public String getDb40_1322() {
        return db40_1322;
    }

    public void setDb40_1322(String db40_1322) {
        this.db40_1322 = db40_1322;
    }

    public String getDb40_1326() {
        return db40_1326;
    }

    public void setDb40_1326(String db40_1326) {
        this.db40_1326 = db40_1326;
    }

    public String getDb40_1548() {
        return db40_1548;
    }

    public void setDb40_1548(String db40_1548) {
        this.db40_1548 = db40_1548;
    }

    public String getDb40_240() {
        return db40_240;
    }

    public void setDb40_240(String db40_240) {
        this.db40_240 = db40_240;
    }

    public String getDb40_80() {
        return db40_80;
    }

    public void setDb40_80(String db40_80) {
        this.db40_80 = db40_80;
    }

    public String getDb40_184() {
        return db40_184;
    }

    public void setDb40_184(String db40_184) {
        this.db40_184 = db40_184;
    }

    public String getDb40_84() {
        return db40_84;
    }

    public void setDb40_84(String db40_84) {
        this.db40_84 = db40_84;
    }

    public String getDb40_92() {
        return db40_92;
    }

    public void setDb40_92(String db40_92) {
        this.db40_92 = db40_92;
    }

    public String getDb40_88() {
        return db40_88;
    }

    public void setDb40_88(String db40_88) {
        this.db40_88 = db40_88;
    }

    public String getDb40_1556() {
        return db40_1556;
    }

    public void setDb40_1556(String db40_1556) {
        this.db40_1556 = db40_1556;
    }

    public String getDb40_1552() {
        return db40_1552;
    }

    public void setDb40_1552(String db40_1552) {
        this.db40_1552 = db40_1552;
    }

    public String getDb40_248() {
        return db40_248;
    }

    public void setDb40_248(String db40_248) {
        this.db40_248 = db40_248;
    }

    public String getDb40_96() {
        return db40_96;
    }

    public void setDb40_96(String db40_96) {
        this.db40_96 = db40_96;
    }

    public String getDb40_244() {
        return db40_244;
    }

    public void setDb40_244(String db40_244) {
        this.db40_244 = db40_244;
    }

    public String getDb40_100() {
        return db40_100;
    }

    public void setDb40_100(String db40_100) {
        this.db40_100 = db40_100;
    }

    public String getDb40_108() {
        return db40_108;
    }

    public void setDb40_108(String db40_108) {
        this.db40_108 = db40_108;
    }

    public String getDb40_104() {
        return db40_104;
    }

    public void setDb40_104(String db40_104) {
        this.db40_104 = db40_104;
    }

    public String getDb40_1560() {
        return db40_1560;
    }

    public void setDb40_1560(String db40_1560) {
        this.db40_1560 = db40_1560;
    }

    public String getDb49_80() {
        return db49_80;
    }

    public void setDb49_80(String db49_80) {
        this.db49_80 = db49_80;
    }

    public String getDb49_84() {
        return db49_84;
    }

    public void setDb49_84(String db49_84) {
        this.db49_84 = db49_84;
    }

    public String getDb49_88() {
        return db49_88;
    }

    public void setDb49_88(String db49_88) {
        this.db49_88 = db49_88;
    }

    public String getDb49_92() {
        return db49_92;
    }

    public void setDb49_92(String db49_92) {
        this.db49_92 = db49_92;
    }

    public String getDb49_96() {
        return db49_96;
    }

    public void setDb49_96(String db49_96) {
        this.db49_96 = db49_96;
    }

    public String getDb49_100() {
        return db49_100;
    }

    public void setDb49_100(String db49_100) {
        this.db49_100 = db49_100;
    }

    public String getDb49_204() {
        return db49_204;
    }

    public void setDb49_204(String db49_204) {
        this.db49_204 = db49_204;
    }

    public String getDb49_208() {
        return db49_208;
    }

    public void setDb49_208(String db49_208) {
        this.db49_208 = db49_208;
    }

    public String getDb49_212() {
        return db49_212;
    }

    public void setDb49_212(String db49_212) {
        this.db49_212 = db49_212;
    }

    public String getDb49_216() {
        return db49_216;
    }

    public void setDb49_216(String db49_216) {
        this.db49_216 = db49_216;
    }

    public String getDb49_280() {
        return db49_280;
    }

    public void setDb49_280(String db49_280) {
        this.db49_280 = db49_280;
    }

    public String getDb49_284() {
        return db49_284;
    }

    public void setDb49_284(String db49_284) {
        this.db49_284 = db49_284;
    }

    public String getDb49_288() {
        return db49_288;
    }

    public void setDb49_288(String db49_288) {
        this.db49_288 = db49_288;
    }

    public String getDb49_292() {
        return db49_292;
    }

    public void setDb49_292(String db49_292) {
        this.db49_292 = db49_292;
    }

    public String getDb49_296() {
        return db49_296;
    }

    public void setDb49_296(String db49_296) {
        this.db49_296 = db49_296;
    }

    public String getDb49_300() {
        return db49_300;
    }

    public void setDb49_300(String db49_300) {
        this.db49_300 = db49_300;
    }

    public String getDb49_180() {
        return db49_180;
    }

    public void setDb49_180(String db49_180) {
        this.db49_180 = db49_180;
    }

    public String getDb49_184() {
        return db49_184;
    }

    public void setDb49_184(String db49_184) {
        this.db49_184 = db49_184;
    }

    public String getDb49_116() {
        return db49_116;
    }

    public void setDb49_116(String db49_116) {
        this.db49_116 = db49_116;
    }

    public String getDb49_120() {
        return db49_120;
    }

    public void setDb49_120(String db49_120) {
        this.db49_120 = db49_120;
    }

    public String getDb49_156() {
        return db49_156;
    }

    public void setDb49_156(String db49_156) {
        this.db49_156 = db49_156;
    }

    public String getDb49_160() {
        return db49_160;
    }

    public void setDb49_160(String db49_160) {
        this.db49_160 = db49_160;
    }

    public String getDb49_136() {
        return db49_136;
    }

    public void setDb49_136(String db49_136) {
        this.db49_136 = db49_136;
    }

    public String getDb49_124() {
        return db49_124;
    }

    public void setDb49_124(String db49_124) {
        this.db49_124 = db49_124;
    }

    public String getDb49_132() {
        return db49_132;
    }

    public void setDb49_132(String db49_132) {
        this.db49_132 = db49_132;
    }

    public String getDb49_128() {
        return db49_128;
    }

    public void setDb49_128(String db49_128) {
        this.db49_128 = db49_128;
    }

    public String getDb49_240() {
        return db49_240;
    }

    public void setDb49_240(String db49_240) {
        this.db49_240 = db49_240;
    }

    public String getDb49_244() {
        return db49_244;
    }

    public void setDb49_244(String db49_244) {
        this.db49_244 = db49_244;
    }

    public String getDb49_328() {
        return db49_328;
    }

    public void setDb49_328(String db49_328) {
        this.db49_328 = db49_328;
    }

    public String getDb49_332() {
        return db49_332;
    }

    public void setDb49_332(String db49_332) {
        this.db49_332 = db49_332;
    }

    public String getDb47_4() {
        return db47_4;
    }

    public void setDb47_4(String db47_4) {
        this.db47_4 = db47_4;
    }

    public String getDb47_0() {
        return db47_0;
    }

    public void setDb47_0(String db47_0) {
        this.db47_0 = db47_0;
    }

    public String getDb47_52() {
        return db47_52;
    }

    public void setDb47_52(String db47_52) {
        this.db47_52 = db47_52;
    }

    public String getDb47_76() {
        return db47_76;
    }

    public void setDb47_76(String db47_76) {
        this.db47_76 = db47_76;
    }

    public String getDb47_64() {
        return db47_64;
    }

    public void setDb47_64(String db47_64) {
        this.db47_64 = db47_64;
    }

    public String getDb47_56() {
        return db47_56;
    }

    public void setDb47_56(String db47_56) {
        this.db47_56 = db47_56;
    }

    public String getDb47_80() {
        return db47_80;
    }

    public void setDb47_80(String db47_80) {
        this.db47_80 = db47_80;
    }

    public String getDb47_16() {
        return db47_16;
    }

    public void setDb47_16(String db47_16) {
        this.db47_16 = db47_16;
    }

    public String getDb47_256() {
        return db47_256;
    }

    public void setDb47_256(String db47_256) {
        this.db47_256 = db47_256;
    }

    public String getDb46_36() {
        return db46_36;
    }

    public void setDb46_36(String db46_36) {
        this.db46_36 = db46_36;
    }

    public String getDb46_40() {
        return db46_40;
    }

    public void setDb46_40(String db46_40) {
        this.db46_40 = db46_40;
    }

    public String getDb46_44() {
        return db46_44;
    }

    public void setDb46_44(String db46_44) {
        this.db46_44 = db46_44;
    }

    public String getDb46_48() {
        return db46_48;
    }

    public void setDb46_48(String db46_48) {
        this.db46_48 = db46_48;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public ShieldEs() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
