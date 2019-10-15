package com.secusoft.ssw.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perimeterPic")
@Api(value = "PerimeterPictures-Controller",description = "周界图片管理相关接口")
public class PerimeterPicturesController extends BaseController{
}
