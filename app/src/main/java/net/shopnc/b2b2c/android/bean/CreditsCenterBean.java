package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/10/11.
 */

public class CreditsCenterBean {

    /**
     * member_info : {"member_id":"220","user_name":"qiangudamotou","avatar":"http://test.shopnctest.com/data/upload/shop/common/default_user_portrait.gif","level":0,"level_name":"V0","member_points":"110","member_exppoints":25}
     * voucher : [{"voucher_t_id":"13","voucher_t_title":"双11大促 满100减50","voucher_t_desc":"双11大促 满100减500","voucher_t_start_date":"1476001220","voucher_t_end_date":"2018-10-31","voucher_t_price":"50","voucher_t_limit":"100.00","voucher_t_store_id":"1","voucher_t_storename":"平台自营","voucher_t_sc_id":"6","voucher_t_creator_id":"1","voucher_t_state":"1","voucher_t_total":"1000","voucher_t_giveout":"2","voucher_t_used":"0","voucher_t_add_date":"1476001253","voucher_t_quotaid":"0","voucher_t_points":"50","voucher_t_eachlimit":"1","voucher_t_styleimg":null,"voucher_t_customimg":"http://test.shopnctest.com/data/upload/shop/voucher/1/05293452205436481.png","voucher_t_recommend":"0","voucher_t_gettype":"1","voucher_t_isbuild":"0","voucher_t_mgradelimit":"0","voucher_t_sc_name":"食品/保健","voucher_t_gettype_key":"points","voucher_t_gettype_text":"积分兑换","voucher_t_state_text":"有效","voucher_t_mgradelimittext":"V0"},{"voucher_t_id":"12","voucher_t_title":"双11大促 满50减10元","voucher_t_desc":"双11大促 满50减10元","voucher_t_start_date":"1476001113","voucher_t_end_date":"2018-10-31","voucher_t_price":"10","voucher_t_limit":"50.00","voucher_t_store_id":"1","voucher_t_storename":"平台自营","voucher_t_sc_id":"3","voucher_t_creator_id":"1","voucher_t_state":"1","voucher_t_total":"1000","voucher_t_giveout":"4","voucher_t_used":"0","voucher_t_add_date":"1476001113","voucher_t_quotaid":"0","voucher_t_points":"0","voucher_t_eachlimit":"1","voucher_t_styleimg":null,"voucher_t_customimg":"http://test.shopnctest.com/data/upload/shop/voucher/1/05293451139217882.png","voucher_t_recommend":"0","voucher_t_gettype":"1","voucher_t_isbuild":"0","voucher_t_mgradelimit":"0","voucher_t_sc_name":"3C数码","voucher_t_gettype_key":"points","voucher_t_gettype_text":"积分兑换","voucher_t_state_text":"有效","voucher_t_mgradelimittext":"V0"},{"voucher_t_id":"11","voucher_t_title":"双11大促 满200减100","voucher_t_desc":"双11大促，全场满200减100元","voucher_t_start_date":"1475998864","voucher_t_end_date":"2018-10-31","voucher_t_price":"100","voucher_t_limit":"200.00","voucher_t_store_id":"1","voucher_t_storename":"平台自营","voucher_t_sc_id":"2","voucher_t_creator_id":"1","voucher_t_state":"1","voucher_t_total":"1000","voucher_t_giveout":"0","voucher_t_used":"0","voucher_t_add_date":"1475999164","voucher_t_quotaid":"0","voucher_t_points":"100","voucher_t_eachlimit":"1","voucher_t_styleimg":null,"voucher_t_customimg":"http://test.shopnctest.com/data/upload/shop/voucher/1/05293431641341331.png","voucher_t_recommend":"0","voucher_t_gettype":"1","voucher_t_isbuild":"0","voucher_t_mgradelimit":"0","voucher_t_sc_name":"服装鞋包","voucher_t_gettype_key":"points","voucher_t_gettype_text":"积分兑换","voucher_t_state_text":"有效","voucher_t_mgradelimittext":"V0"}]
     * pointsprod : [{"pgoods_id":"10","pgoods_name":"酸梅汤 山楂乌梅茶 原料浓缩 速溶茶饮料","pgoods_price":"16.00","pgoods_points":"1600","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_mid.jpg","pgoods_tag":"","pgoods_serial":"SMT215424","pgoods_storage":"999","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409643776","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877600755163.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877600755163.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877601391042.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877601391042.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877602067574.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629877602067574.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"1","pgoods_view":"2","pgoods_islimit":"0","pgoods_limitnum":"0","pgoods_islimittime":"0","pgoods_limitmgrade":"0","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04629877761622244.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_small.jpg","ex_state":"going"},{"pgoods_id":"9","pgoods_name":"联想ThinkPad无线激光鼠标 经典小黑无线版 游戏办公家用","pgoods_price":"49.00","pgoods_points":"4800","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_mid.jpg","pgoods_tag":"","pgoods_serial":"TMJ21254525","pgoods_storage":"10000","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409642786","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629867793128891.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629867793128891.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629867793809658.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629867793809658.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"13","pgoods_islimit":"0","pgoods_limitnum":"0","pgoods_islimittime":"0","pgoods_limitmgrade":"1","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04629867864024255.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_small.jpg","ex_state":"going","pgoods_limitgradename":"V1"},{"pgoods_id":"8","pgoods_name":"富光玻璃杯正品 520ml大容量双层透明隔热带盖过滤带滤网玻璃水杯","pgoods_price":"29.00","pgoods_points":"2900","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629862424354709_mid.jpg","pgoods_tag":"","pgoods_serial":"FGB12154235","pgoods_storage":"10000","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409642242","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629862355028405.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629862355028405.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629862382479304.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629862382479304.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"4","pgoods_islimit":"0","pgoods_limitnum":"0","pgoods_islimittime":"1","pgoods_limitmgrade":"0","pgoods_starttime":"1409500800","pgoods_endtime":"1443542400","pgoods_sort":"0","pgoods_image_old":"04629862424354709.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629862424354709_small.jpg","ex_state":"end"},{"pgoods_id":"7","pgoods_name":"保温杯女士可爱 不锈钢大肚杯 情侣创意水杯","pgoods_price":"36.00","pgoods_points":"3600","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629848661300835_mid.jpg","pgoods_tag":"","pgoods_serial":"QLB5527498","pgoods_storage":"10000","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409640866","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848529735488.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848529735488.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848530364627.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848530364627.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848531204985.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848531204985.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848531956300.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04629848531956300.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"9","pgoods_islimit":"0","pgoods_limitnum":"0","pgoods_islimittime":"0","pgoods_limitmgrade":"2","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04629848661300835.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629848661300835_small.jpg","ex_state":"going","pgoods_limitgradename":"V2"},{"pgoods_id":"6","pgoods_name":"糖果枕 颈椎病专用枕头 护颈椎保健枕 竹炭枕头 颈椎治疗枕","pgoods_price":"20.00","pgoods_points":"2000","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626497200595517_mid.jpg","pgoods_tag":"","pgoods_serial":"ZT2545548","pgoods_storage":"10000","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409305720","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496890179926.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496890179926.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496918657969.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496918657969.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496943499815.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626496943499815.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626497125341202.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626497125341202.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"26","pgoods_islimit":"0","pgoods_limitnum":"0","pgoods_islimittime":"0","pgoods_limitmgrade":"0","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04626497200595517.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626497200595517_small.jpg","ex_state":"going"},{"pgoods_id":"5","pgoods_name":"2014新品时尚韩版潮小包女包 多层手挽包 欧美范单肩手提包","pgoods_price":"58.00","pgoods_points":"5800","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626488262089512_mid.jpg","pgoods_tag":"","pgoods_serial":"MKN15524125","pgoods_storage":"1000","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409304826","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626488189540404.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626488189540404.jpg\" /><img src=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626488216361875.jpg\" alt=\"http://test.shopnctest.com/data/upload/shop/pointprod/04626488216361875.jpg\" />","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"2","pgoods_islimit":"1","pgoods_limitnum":"5","pgoods_islimittime":"1","pgoods_limitmgrade":"1","pgoods_starttime":"1409241600","pgoods_endtime":"1440950400","pgoods_sort":"0","pgoods_image_old":"04626488262089512.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626488262089512_small.jpg","ex_state":"end","pgoods_limitgradename":"V1"},{"pgoods_id":"4","pgoods_name":"久量小夜灯 LED-405光控LED节能","pgoods_price":"19.00","pgoods_points":"800","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626364338262304_mid.jpg","pgoods_tag":"","pgoods_serial":"201400903","pgoods_storage":"603","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409292433","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g12/M00/08/18/rBEQYVNOKZ8IAAAAAAJ68dFilwEAAEiRABv2I0AAnsJ600.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_rb\">\r\n\t\t<span>产品信息<\/span><span class=\"s2\">Product Information<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<table class=\"ke-zeroborder\" style=\"margin:auto;\" border=\"0\" cellpadding=\"0\" cellspacing=\"6\" width=\"750\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g13/M08/06/05/rBEhU1NOKbwIAAAAAAEe1pY5tWIAAL6tAClD1kAAR7u281.jpg\" alt=\"image\" /> \r\n\t\t\t<\/td>\r\n\t\t\t<td>\r\n\t\t\t\t<p class=\"formwork_titleleft\">\r\n\t\t\t\t\t久量小夜灯 LED-403光控LED节能\r\n\t\t\t\t<\/p>\r\n\t\t\t\t<p class=\"formwork_titleleft2\">\r\n\t\t\t\t\t型号：LED-403<br />\r\n电压：AC90~240V 50Hz/60Hz<br />\r\n电流：AC0.02A<br />\r\n光源：0.3W\r\n\t\t\t\t<\/p>\r\n\t\t\t<\/td>\r\n\t\t<\/tr>\r\n\t<\/tbody>\r\n<\/table>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_rb\">\r\n\t\t<span>产品展示<\/span><span class=\"s2\">Products Exhibition<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g15/M00/16/1A/rBEhWVNOK-cIAAAAAANT3SM4p2IAAL6NQL8zikAA1P1992.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M02/1D/09/rBEhV1NOK_oIAAAAAALcsqHI1T0AAMDMgC6l24AAtzK068.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g15/M03/16/1A/rBEhWFNOLA8IAAAAAAO4a4RjnAIAAL6OQBxUXcAA7iD338.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M09/1D/09/rBEhV1NOLBwIAAAAAAH7cXzCOR8AAMDNQOjjEoAAfuJ238.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_rb\">\r\n\t\t<span>产品细节<\/span><span class=\"s2\">Product Details<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g13/M06/06/05/rBEhUlNOKuMIAAAAAAEQp_Big8sAAL6wwMZAJcAARC_606.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M02/1D/09/rBEhVVNOKvUIAAAAAAFfcSuC4lwAAMDJQMkl-8AAV-J527.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M08/1D/09/rBEhVVNOKwUIAAAAAAE7oSLBo38AAMDKAA2OH0AATu5066.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M05/1D/09/rBEhVVNOKxUIAAAAAAF3rGqO3VUAAMDKgKhgrEAAXfE403.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_rb\">\r\n\t\t<span><\/span><span class=\"s2\"><br />\r\n<\/span> \r\n\t<\/div>\r\n<\/div>","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"17","pgoods_islimit":"1","pgoods_limitnum":"3","pgoods_islimittime":"1","pgoods_limitmgrade":"0","pgoods_starttime":"1409241600","pgoods_endtime":"1420041600","pgoods_sort":"0","pgoods_image_old":"04626364338262304.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626364338262304_small.jpg","ex_state":"end"},{"pgoods_id":"3","pgoods_name":"Gucci古奇时尚女包/手提包F码","pgoods_price":"0.00","pgoods_points":"1600","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626276477182577_mid.jpg","pgoods_tag":"","pgoods_serial":"aaaaaa","pgoods_storage":"10","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409283647","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<div>\r\n\t<p>\r\n\t\t<span style=\"color:#E53333;font-size:24px;\">只是优先购买F码，购买后通过站内信发送，只有10张，先到先得。<\/span>\r\n\t<\/p>\r\n\t<p>\r\n\t\t<img src=\"http://img03.taobaocdn.com/imgextra/i3/1963598548/T2K9a2XpdXXXXXXXXX_%21%211963598548.jpg\" alt=\"image\" /> \r\n\t<\/p>\r\n<\/div>\r\n<div style=\"margin:auto;\">\r\n\t.<img src=\"http://img01.taobaocdn.com/imgextra/i1/1963598548/T21It8XOtXXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img01.taobaocdn.com/imgextra/i1/1963598548/T2mkB8XLBXXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img04.taobaocdn.com/imgextra/i4/1963598548/T2hj48XMXXXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img03.taobaocdn.com/imgextra/i3/1963598548/T2jBl.XGlXXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img03.taobaocdn.com/imgextra/i3/1963598548/T2FOJ5XIXaXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img03.taobaocdn.com/imgextra/i3/1963598548/T2opR.XIpXXXXXXXXX-1963598548.jpg\" alt=\"image\" /><img src=\"http://img04.taobaocdn.com/imgextra/i4/1963598548/T2Cqd5XKVaXXXXXXXX-1963598548.jpg\" alt=\"image\" /> \r\n<\/div>\r\n<div>\r\n\t<img src=\"http://img01.taobaocdn.com/imgextra/i1/1963598548/T21Gi2XtXaXXXXXXXX_%21%211963598548.jpg\" alt=\"image\" /> \r\n<\/div>\r\n<div style=\"text-align:left;\">\r\n\t.\r\n<\/div>\r\n<div style=\"text-align:left;\">\r\n\t. &nbsp; blue GG denim with maple brown leather detail<br />\r\n&nbsp;&nbsp;&nbsp; antique gold hardware<br />\r\n&nbsp;&nbsp;&nbsp; natural cotton linen lining<br />\r\n&nbsp;&nbsp;&nbsp; Made in Italy<br />\r\n&nbsp;&nbsp;&nbsp; double handles with 3.5\" drop<br />\r\n&nbsp;&nbsp;&nbsp; adjustable and detachable shoulder strap with 20\" drop<br />\r\n&nbsp;&nbsp;&nbsp; zip-top closure<br />\r\n&nbsp;&nbsp;&nbsp; interior zip and smart phone pockets<br />\r\n&nbsp;&nbsp;&nbsp; medium size: 13\"W x 8.7\"H x 7\"W\r\n<\/div>\r\n<div style=\"text-align:left;\">\r\n\t&nbsp;\r\n<\/div>","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"28","pgoods_islimit":"1","pgoods_limitnum":"1","pgoods_islimittime":"0","pgoods_limitmgrade":"1","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04626276477182577.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626276477182577_small.jpg","ex_state":"going","pgoods_limitgradename":"V1"},{"pgoods_id":"2","pgoods_name":"得力（deli）3680 USB全金属迷你桌面型USB小风扇 蓝色 1只/盒装","pgoods_price":"25.00","pgoods_points":"2000","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626264083026214_mid.jpg","pgoods_tag":"","pgoods_serial":"201400901","pgoods_storage":"600","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409282408","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品信息<\/span><span class=\"s2\">Product Information<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-ctUIAAAAAAImXbS4qiEAAB6ygILioUAAiZ1926.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M08/01/18/rBEhV1HBJQMIAAAAAABpIjOB1QQAAAWIQGSbqEAAGk6177.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>尺寸信息<\/span><span class=\"s2\">Size Information<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g14/M08/01/18/rBEhV1HBJQkIAAAAAAIuKeCJo3AAAAWIQJoxKEAAi5B185.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t俯卧式360度节点调节风向，自由选择\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品展示<\/span><span class=\"s2\">Products Exhibition<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t得力3680风扇有白色、蓝色、黑色三种配色，颜色沉稳商务\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-cz8IAAAAAAIrU17451YAAB6ygMQEaEAAitr924.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品细节<\/span><span class=\"s2\">Product Details<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<table class=\"ke-zeroborder\" style=\"margin:auto;\" border=\"0\" cellpadding=\"0\" cellspacing=\"6\" width=\"750\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-c2YIAAAAAABMMBlrnMsAAB6ygNEmaAAAExI128.jpg\" alt=\"image\" /> \r\n\t\t\t<\/td>\r\n\t\t\t<td>\r\n\t\t\t\t<p class=\"formwork_titleleft\">\r\n\t\t\t\t\t优质网罩\r\n\t\t\t\t<\/p>\r\n\t\t\t\t<p class=\"formwork_titleleft2\">\r\n\t\t\t\t\t全金属喷漆结构壳体\r\n\t\t\t\t<\/p>\r\n\t\t\t<\/td>\r\n\t\t<\/tr>\r\n\t<\/tbody>\r\n<\/table>\r\n<table class=\"ke-zeroborder\" style=\"margin:auto;\" border=\"0\" cellpadding=\"0\" cellspacing=\"6\" width=\"750\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<p class=\"formwork_titleleft\">\r\n\t\t\t\t\t橡胶脚\r\n\t\t\t\t<\/p>\r\n\t\t\t\t<p class=\"formwork_titleleft2\">\r\n\t\t\t\t\t橡胶脚，防震防滑，金属支架更加稳固\r\n\t\t\t\t<\/p>\r\n\t\t\t<\/td>\r\n\t\t\t<td>\r\n\t\t\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-c4YIAAAAAAA0gO5Q-pQAAB6ygNtOHUAADSY616.jpg\" alt=\"image\" /> \r\n\t\t\t<\/td>\r\n\t\t<\/tr>\r\n\t<\/tbody>\r\n<\/table>\r\n<table class=\"ke-zeroborder\" style=\"margin:auto;\" border=\"0\" cellpadding=\"0\" cellspacing=\"6\" width=\"750\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-c7UIAAAAAABEFwW1MBYAAB6ygPy1m0AAEQv985.jpg\" alt=\"image\" /> \r\n\t\t\t<\/td>\r\n\t\t\t<td>\r\n\t\t\t\t<p class=\"formwork_titleleft\">\r\n\t\t\t\t\t叶片\r\n\t\t\t\t<\/p>\r\n\t\t\t\t<p class=\"formwork_titleleft2\">\r\n\t\t\t\t\t铝金属叶片，风力更强劲\r\n\t\t\t\t<\/p>\r\n\t\t\t<\/td>\r\n\t\t<\/tr>\r\n\t<\/tbody>\r\n<\/table>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>附件清单<\/span><span class=\"s2\">Accessories<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWVE-c-4IAAAAAAIxuwwXacsAAB6ywA3xI8AAjHT352.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t配件中包含一个风扇、一条USB连接线、一张合格证、一本说明书\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品包装<\/span><span class=\"s2\">packing detail<\/span> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img class=\"err-product\" src=\"http://img20.360buyimg.com/vc/g10/M00/09/1A/rBEQWFE-dA0IAAAAAAIwd7Y4IeQAAB6ywCVhWMAAjCP577.jpg\" alt=\"image\" /> \r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t<br />\r\n\t<\/div>\r\n<\/div>","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"5","pgoods_islimit":"1","pgoods_limitnum":"1","pgoods_islimittime":"0","pgoods_limitmgrade":"1","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04626264083026214.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626264083026214_small.jpg","ex_state":"going","pgoods_limitgradename":"V1"},{"pgoods_id":"1","pgoods_name":"罗技（Logitech）无线键鼠套装 MK240（白色）","pgoods_price":"99.00","pgoods_points":"9900","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626237423027045_mid.jpg","pgoods_tag":"","pgoods_serial":"20140829","pgoods_storage":"13","pgoods_show":"1","pgoods_commend":"1","pgoods_add_time":"1409279742","pgoods_keywords":"","pgoods_description":"","pgoods_body":"<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品信息<\/span><span class=\"s2\">Product Information<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M01/13/10/rBEhVFLotpwIAAAAAAF-gtTmmfAAAIRhAHpWR0AAX6a061.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M01/13/10/rBEhU1LotqUIAAAAAAG6kSGHY90AAIRhAJXQsgAAbqp049.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品功能<\/span><span class=\"s2\">Product Function<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M03/0E/18/rBEhVVLotrYIAAAAAAJuNUgfEScAAIYDwGdW64AAm5N603.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品细节<\/span><span class=\"s2\">Product Details<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M01/13/10/rBEhVFLot2QIAAAAAAEnFUCAu-wAAIRhAP-PZMAASct079.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t简约型键盘&amp;\r\n紧凑型键盘&amp;\r\n全尺寸键盘\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M02/13/10/rBEhUlLot70IAAAAAAFKX_TTGbsAAIRhQFf_l4AAUp3360.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M02/13/10/rBEhVFLot8QIAAAAAACTLPUqksoAAIRhQGH-PYAAJNE724.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M02/13/10/rBEhVFLot90IAAAAAAF4EqIkN3kAAIRhQJ2x_oAAXgq188.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_text\">\r\n\t\t强大可靠的无线连接\r\n罗技先进的2.4GHz无线技术可实现几乎无干扰和掉帧的强大可靠的无线连接（可长达10米），\r\n即使是在拥挤的无线环境也不例外。\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g12/M00/02/1E/rBEQYFMz7coIAAAAAAIItfSTQNUAADZwQHYAgwAAgjN789.gif\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>实景展示<\/span><span class=\"s2\">Virtual show<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M0A/13/10/rBEhVFLouIYIAAAAAAEzBfVeyyQAAIRhgEDPxIAATMd499.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g13/M0A/13/10/rBEhUlLouJAIAAAAAAG8k0Wb2IIAAIRhgFwvKsAAbyr993.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhVlLouKEIAAAAAAJBs0r7MY4AAIYEQBBbJIAAkHL200.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>产品功能<\/span><span class=\"s2\">Product Function<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhV1LouRMIAAAAAAICtKoOUZQAAIYEQG298YAAgLM503.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhVVLouR0IAAAAAAChCYo1JZUAAIYEQIJej0AAKEh764.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork_bt\">\r\n\t<div class=\"formwork_bt_it\">\r\n\t\t<span>使用说明<\/span><span class=\"s2\">Instructions<\/span>\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhVlLouTkIAAAAAACoFFhRHLMAAIYEQKwSgkAAKgs213.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhVlLouUIIAAAAAABlS0Ja8NsAAIYEQLcHJoAAGVj371.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>\r\n<div class=\"formwork\">\r\n\t<div class=\"formwork_img\">\r\n\t\t<img src=\"http://img20.360buyimg.com/vc/g14/M05/0E/18/rBEhVVLouUoIAAAAAADC4_ZS3DcAAIYEQLi3R4AAML7510.jpg\" alt=\"image\" />\r\n\t<\/div>\r\n<\/div>","pgoods_state":"0","pgoods_close_reason":"","pgoods_salenum":"0","pgoods_view":"9","pgoods_islimit":"1","pgoods_limitnum":"1","pgoods_islimittime":"0","pgoods_limitmgrade":"2","pgoods_starttime":"0","pgoods_endtime":"0","pgoods_sort":"0","pgoods_image_old":"04626237423027045.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626237423027045_small.jpg","ex_state":"going","pgoods_limitgradename":"V2"}]
     * redpacket : [{"rpacket_t_id":"2","rpacket_t_title":"周年庆100元红包","rpacket_t_desc":"周年庆百元红包","rpacket_t_start_date":"1475942400","rpacket_t_end_date":"2018-10-31","rpacket_t_price":"100.00","rpacket_t_limit":"150.00","rpacket_t_adminid":"1","rpacket_t_state":"1","rpacket_t_total":"10000","rpacket_t_giveout":"2","rpacket_t_used":"1","rpacket_t_updatetime":"1476001478","rpacket_t_points":"10","rpacket_t_eachlimit":"1","rpacket_t_customimg":"05293454781970862.png","rpacket_t_recommend":"1","rpacket_t_gettype":"1","rpacket_t_isbuild":"0","rpacket_t_mgradelimit":"0","rpacket_t_customimg_url":"http://test.shopnctest.com/data/upload/shop/redpacket/05293454781970862.png","rpacket_t_gettype_key":"points","rpacket_t_gettype_text":"积分兑换","rpacket_t_state_text":"有效","rpacket_t_mgradelimittext":"V0"},{"rpacket_t_id":"4","rpacket_t_title":"新人加油包","rpacket_t_desc":"10元","rpacket_t_start_date":"1475942400","rpacket_t_end_date":"2018-10-31","rpacket_t_price":"10.00","rpacket_t_limit":"20.00","rpacket_t_adminid":"1","rpacket_t_state":"1","rpacket_t_total":"10000","rpacket_t_giveout":"1","rpacket_t_used":"0","rpacket_t_updatetime":"1476001893","rpacket_t_points":"1","rpacket_t_eachlimit":"1","rpacket_t_customimg":"05293458933995697.png","rpacket_t_recommend":"0","rpacket_t_gettype":"1","rpacket_t_isbuild":"0","rpacket_t_mgradelimit":"0","rpacket_t_customimg_url":"http://test.shopnctest.com/data/upload/shop/redpacket/05293458933995697.png","rpacket_t_gettype_key":"points","rpacket_t_gettype_text":"积分兑换","rpacket_t_state_text":"有效","rpacket_t_mgradelimittext":"V0"},{"rpacket_t_id":"3","rpacket_t_title":"空调优惠券500元","rpacket_t_desc":"空调优惠券500元","rpacket_t_start_date":"1475942400","rpacket_t_end_date":"2018-10-31","rpacket_t_price":"500.00","rpacket_t_limit":"2000.00","rpacket_t_adminid":"1","rpacket_t_state":"1","rpacket_t_total":"10000","rpacket_t_giveout":"0","rpacket_t_used":"0","rpacket_t_updatetime":"1476001777","rpacket_t_points":"200","rpacket_t_eachlimit":"1","rpacket_t_customimg":"05293457771573919.png","rpacket_t_recommend":"0","rpacket_t_gettype":"1","rpacket_t_isbuild":"0","rpacket_t_mgradelimit":"0","rpacket_t_customimg_url":"http://test.shopnctest.com/data/upload/shop/redpacket/05293457771573919.png","rpacket_t_gettype_key":"points","rpacket_t_gettype_text":"积分兑换","rpacket_t_state_text":"有效","rpacket_t_mgradelimittext":"V0"}]
     * vouchercount : 2
     * pointcart_count : 0
     * pointordercount : 0
     * redpacketcount : 1
     */

    private MemberInfoBean member_info;
    private int vouchercount;
    private int pointcart_count;
    private int pointordercount;
    private int redpacketcount;
    private List<VoucherBean> voucher;
    private List<PointsprodBean> pointsprod;
    private List<RedpacketBean> redpacket;

    public MemberInfoBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberInfoBean member_info) {
        this.member_info = member_info;
    }

    public int getVouchercount() {
        return vouchercount;
    }

    public void setVouchercount(int vouchercount) {
        this.vouchercount = vouchercount;
    }

    public int getPointcart_count() {
        return pointcart_count;
    }

    public void setPointcart_count(int pointcart_count) {
        this.pointcart_count = pointcart_count;
    }

    public int getPointordercount() {
        return pointordercount;
    }

    public void setPointordercount(int pointordercount) {
        this.pointordercount = pointordercount;
    }

    public int getRedpacketcount() {
        return redpacketcount;
    }

    public void setRedpacketcount(int redpacketcount) {
        this.redpacketcount = redpacketcount;
    }

    public List<VoucherBean> getVoucher() {
        return voucher;
    }

    public void setVoucher(List<VoucherBean> voucher) {
        this.voucher = voucher;
    }

    public List<PointsprodBean> getPointsprod() {
        return pointsprod;
    }

    public void setPointsprod(List<PointsprodBean> pointsprod) {
        this.pointsprod = pointsprod;
    }

    public List<RedpacketBean> getRedpacket() {
        return redpacket;
    }

    public void setRedpacket(List<RedpacketBean> redpacket) {
        this.redpacket = redpacket;
    }

    public static class MemberInfoBean {
        /**
         * member_id : 220
         * user_name : qiangudamotou
         * avatar : http://test.shopnctest.com/data/upload/shop/common/default_user_portrait.gif
         * level : 0
         * level_name : V0
         * member_points : 110
         * member_exppoints : 25
         */

        private String member_id;
        private String user_name;
        private String avatar;
        private int level;
        private String level_name;
        private String member_points;
        private int member_exppoints;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getMember_points() {
            return member_points;
        }

        public void setMember_points(String member_points) {
            this.member_points = member_points;
        }

        public int getMember_exppoints() {
            return member_exppoints;
        }

        public void setMember_exppoints(int member_exppoints) {
            this.member_exppoints = member_exppoints;
        }
    }

    public static class VoucherBean {
        /**
         * voucher_t_id : 13
         * voucher_t_title : 双11大促 满100减50
         * voucher_t_desc : 双11大促 满100减500
         * voucher_t_start_date : 1476001220
         * voucher_t_end_date : 2018-10-31
         * voucher_t_price : 50
         * voucher_t_limit : 100.00
         * voucher_t_store_id : 1
         * voucher_t_storename : 平台自营
         * voucher_t_sc_id : 6
         * voucher_t_creator_id : 1
         * voucher_t_state : 1
         * voucher_t_total : 1000
         * voucher_t_giveout : 2
         * voucher_t_used : 0
         * voucher_t_add_date : 1476001253
         * voucher_t_quotaid : 0
         * voucher_t_points : 50
         * voucher_t_eachlimit : 1
         * voucher_t_styleimg : null
         * voucher_t_customimg : http://test.shopnctest.com/data/upload/shop/voucher/1/05293452205436481.png
         * voucher_t_recommend : 0
         * voucher_t_gettype : 1
         * voucher_t_isbuild : 0
         * voucher_t_mgradelimit : 0
         * voucher_t_sc_name : 食品/保健
         * voucher_t_gettype_key : points
         * voucher_t_gettype_text : 积分兑换
         * voucher_t_state_text : 有效
         * voucher_t_mgradelimittext : V0
         */

        private String voucher_t_id;
        private String voucher_t_title;
        private String voucher_t_desc;
        private String voucher_t_start_date;
        private String voucher_t_end_date;
        private String voucher_t_price;
        private String voucher_t_limit;
        private String voucher_t_store_id;
        private String voucher_t_storename;
        private String voucher_t_sc_id;
        private String voucher_t_creator_id;
        private String voucher_t_state;
        private String voucher_t_total;
        private String voucher_t_giveout;
        private String voucher_t_used;
        private String voucher_t_add_date;
        private String voucher_t_quotaid;
        private String voucher_t_points;
        private String voucher_t_eachlimit;
        private Object voucher_t_styleimg;
        private String voucher_t_customimg;
        private String voucher_t_recommend;
        private String voucher_t_gettype;
        private String voucher_t_isbuild;
        private String voucher_t_mgradelimit;
        private String voucher_t_sc_name;
        private String voucher_t_gettype_key;
        private String voucher_t_gettype_text;
        private String voucher_t_state_text;
        private String voucher_t_mgradelimittext;

        public String getVoucher_t_id() {
            return voucher_t_id;
        }

        public void setVoucher_t_id(String voucher_t_id) {
            this.voucher_t_id = voucher_t_id;
        }

        public String getVoucher_t_title() {
            return voucher_t_title;
        }

        public void setVoucher_t_title(String voucher_t_title) {
            this.voucher_t_title = voucher_t_title;
        }

        public String getVoucher_t_desc() {
            return voucher_t_desc;
        }

        public void setVoucher_t_desc(String voucher_t_desc) {
            this.voucher_t_desc = voucher_t_desc;
        }

        public String getVoucher_t_start_date() {
            return voucher_t_start_date;
        }

        public void setVoucher_t_start_date(String voucher_t_start_date) {
            this.voucher_t_start_date = voucher_t_start_date;
        }

        public String getVoucher_t_end_date() {
            return voucher_t_end_date;
        }

        public void setVoucher_t_end_date(String voucher_t_end_date) {
            this.voucher_t_end_date = voucher_t_end_date;
        }

        public String getVoucher_t_price() {
            return voucher_t_price;
        }

        public void setVoucher_t_price(String voucher_t_price) {
            this.voucher_t_price = voucher_t_price;
        }

        public String getVoucher_t_limit() {
            return voucher_t_limit;
        }

        public void setVoucher_t_limit(String voucher_t_limit) {
            this.voucher_t_limit = voucher_t_limit;
        }

        public String getVoucher_t_store_id() {
            return voucher_t_store_id;
        }

        public void setVoucher_t_store_id(String voucher_t_store_id) {
            this.voucher_t_store_id = voucher_t_store_id;
        }

        public String getVoucher_t_storename() {
            return voucher_t_storename;
        }

        public void setVoucher_t_storename(String voucher_t_storename) {
            this.voucher_t_storename = voucher_t_storename;
        }

        public String getVoucher_t_sc_id() {
            return voucher_t_sc_id;
        }

        public void setVoucher_t_sc_id(String voucher_t_sc_id) {
            this.voucher_t_sc_id = voucher_t_sc_id;
        }

        public String getVoucher_t_creator_id() {
            return voucher_t_creator_id;
        }

        public void setVoucher_t_creator_id(String voucher_t_creator_id) {
            this.voucher_t_creator_id = voucher_t_creator_id;
        }

        public String getVoucher_t_state() {
            return voucher_t_state;
        }

        public void setVoucher_t_state(String voucher_t_state) {
            this.voucher_t_state = voucher_t_state;
        }

        public String getVoucher_t_total() {
            return voucher_t_total;
        }

        public void setVoucher_t_total(String voucher_t_total) {
            this.voucher_t_total = voucher_t_total;
        }

        public String getVoucher_t_giveout() {
            return voucher_t_giveout;
        }

        public void setVoucher_t_giveout(String voucher_t_giveout) {
            this.voucher_t_giveout = voucher_t_giveout;
        }

        public String getVoucher_t_used() {
            return voucher_t_used;
        }

        public void setVoucher_t_used(String voucher_t_used) {
            this.voucher_t_used = voucher_t_used;
        }

        public String getVoucher_t_add_date() {
            return voucher_t_add_date;
        }

        public void setVoucher_t_add_date(String voucher_t_add_date) {
            this.voucher_t_add_date = voucher_t_add_date;
        }

        public String getVoucher_t_quotaid() {
            return voucher_t_quotaid;
        }

        public void setVoucher_t_quotaid(String voucher_t_quotaid) {
            this.voucher_t_quotaid = voucher_t_quotaid;
        }

        public String getVoucher_t_points() {
            return voucher_t_points;
        }

        public void setVoucher_t_points(String voucher_t_points) {
            this.voucher_t_points = voucher_t_points;
        }

        public String getVoucher_t_eachlimit() {
            return voucher_t_eachlimit;
        }

        public void setVoucher_t_eachlimit(String voucher_t_eachlimit) {
            this.voucher_t_eachlimit = voucher_t_eachlimit;
        }

        public Object getVoucher_t_styleimg() {
            return voucher_t_styleimg;
        }

        public void setVoucher_t_styleimg(Object voucher_t_styleimg) {
            this.voucher_t_styleimg = voucher_t_styleimg;
        }

        public String getVoucher_t_customimg() {
            return voucher_t_customimg;
        }

        public void setVoucher_t_customimg(String voucher_t_customimg) {
            this.voucher_t_customimg = voucher_t_customimg;
        }

        public String getVoucher_t_recommend() {
            return voucher_t_recommend;
        }

        public void setVoucher_t_recommend(String voucher_t_recommend) {
            this.voucher_t_recommend = voucher_t_recommend;
        }

        public String getVoucher_t_gettype() {
            return voucher_t_gettype;
        }

        public void setVoucher_t_gettype(String voucher_t_gettype) {
            this.voucher_t_gettype = voucher_t_gettype;
        }

        public String getVoucher_t_isbuild() {
            return voucher_t_isbuild;
        }

        public void setVoucher_t_isbuild(String voucher_t_isbuild) {
            this.voucher_t_isbuild = voucher_t_isbuild;
        }

        public String getVoucher_t_mgradelimit() {
            return voucher_t_mgradelimit;
        }

        public void setVoucher_t_mgradelimit(String voucher_t_mgradelimit) {
            this.voucher_t_mgradelimit = voucher_t_mgradelimit;
        }

        public String getVoucher_t_sc_name() {
            return voucher_t_sc_name;
        }

        public void setVoucher_t_sc_name(String voucher_t_sc_name) {
            this.voucher_t_sc_name = voucher_t_sc_name;
        }

        public String getVoucher_t_gettype_key() {
            return voucher_t_gettype_key;
        }

        public void setVoucher_t_gettype_key(String voucher_t_gettype_key) {
            this.voucher_t_gettype_key = voucher_t_gettype_key;
        }

        public String getVoucher_t_gettype_text() {
            return voucher_t_gettype_text;
        }

        public void setVoucher_t_gettype_text(String voucher_t_gettype_text) {
            this.voucher_t_gettype_text = voucher_t_gettype_text;
        }

        public String getVoucher_t_state_text() {
            return voucher_t_state_text;
        }

        public void setVoucher_t_state_text(String voucher_t_state_text) {
            this.voucher_t_state_text = voucher_t_state_text;
        }

        public String getVoucher_t_mgradelimittext() {
            return voucher_t_mgradelimittext;
        }

        public void setVoucher_t_mgradelimittext(String voucher_t_mgradelimittext) {
            this.voucher_t_mgradelimittext = voucher_t_mgradelimittext;
        }
    }

    public static class PointsprodBean {
        /**
         * pgoods_id : 10
         * pgoods_name : 酸梅汤 山楂乌梅茶 原料浓缩 速溶茶饮料
         * pgoods_price : 16.00
         * pgoods_points : 1600
         * pgoods_image : http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_mid.jpg
         * pgoods_tag :
         * pgoods_serial : SMT215424
         * pgoods_storage : 999
         * pgoods_show : 1
         * pgoods_commend : 1
         * pgoods_add_time : 1409643776
         * pgoods_keywords :
         * pgoods_description :
         * pgoods_body : <img src="http://test.shopnctest.com/data/upload/shop/pointprod/04629877600755163.jpg" alt="http://test.shopnctest.com/data/upload/shop/pointprod/04629877600755163.jpg" /><img src="http://test.shopnctest.com/data/upload/shop/pointprod/04629877601391042.jpg" alt="http://test.shopnctest.com/data/upload/shop/pointprod/04629877601391042.jpg" /><img src="http://test.shopnctest.com/data/upload/shop/pointprod/04629877602067574.jpg" alt="http://test.shopnctest.com/data/upload/shop/pointprod/04629877602067574.jpg" />
         * pgoods_state : 0
         * pgoods_close_reason :
         * pgoods_salenum : 1
         * pgoods_view : 2
         * pgoods_islimit : 0
         * pgoods_limitnum : 0
         * pgoods_islimittime : 0
         * pgoods_limitmgrade : 0
         * pgoods_starttime : 0
         * pgoods_endtime : 0
         * pgoods_sort : 0
         * pgoods_image_old : 04629877761622244.jpg
         * pgoods_image_small : http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_small.jpg
         * ex_state : going
         * pgoods_limitgradename : V1
         */

        private String pgoods_id;
        private String pgoods_name;
        private String pgoods_price;
        private String pgoods_points;
        private String pgoods_image;
        private String pgoods_tag;
        private String pgoods_serial;
        private String pgoods_storage;
        private String pgoods_show;
        private String pgoods_commend;
        private String pgoods_add_time;
        private String pgoods_keywords;
        private String pgoods_description;
        private String pgoods_body;
        private String pgoods_state;
        private String pgoods_close_reason;
        private String pgoods_salenum;
        private String pgoods_view;
        private String pgoods_islimit;
        private String pgoods_limitnum;
        private String pgoods_islimittime;
        private String pgoods_limitmgrade;
        private String pgoods_starttime;
        private String pgoods_endtime;
        private String pgoods_sort;
        private String pgoods_image_old;
        private String pgoods_image_small;
        private String ex_state;
        private String pgoods_limitgradename;

        public String getPgoods_id() {
            return pgoods_id;
        }

        public void setPgoods_id(String pgoods_id) {
            this.pgoods_id = pgoods_id;
        }

        public String getPgoods_name() {
            return pgoods_name;
        }

        public void setPgoods_name(String pgoods_name) {
            this.pgoods_name = pgoods_name;
        }

        public String getPgoods_price() {
            return pgoods_price;
        }

        public void setPgoods_price(String pgoods_price) {
            this.pgoods_price = pgoods_price;
        }

        public String getPgoods_points() {
            return pgoods_points;
        }

        public void setPgoods_points(String pgoods_points) {
            this.pgoods_points = pgoods_points;
        }

        public String getPgoods_image() {
            return pgoods_image;
        }

        public void setPgoods_image(String pgoods_image) {
            this.pgoods_image = pgoods_image;
        }

        public String getPgoods_tag() {
            return pgoods_tag;
        }

        public void setPgoods_tag(String pgoods_tag) {
            this.pgoods_tag = pgoods_tag;
        }

        public String getPgoods_serial() {
            return pgoods_serial;
        }

        public void setPgoods_serial(String pgoods_serial) {
            this.pgoods_serial = pgoods_serial;
        }

        public String getPgoods_storage() {
            return pgoods_storage;
        }

        public void setPgoods_storage(String pgoods_storage) {
            this.pgoods_storage = pgoods_storage;
        }

        public String getPgoods_show() {
            return pgoods_show;
        }

        public void setPgoods_show(String pgoods_show) {
            this.pgoods_show = pgoods_show;
        }

        public String getPgoods_commend() {
            return pgoods_commend;
        }

        public void setPgoods_commend(String pgoods_commend) {
            this.pgoods_commend = pgoods_commend;
        }

        public String getPgoods_add_time() {
            return pgoods_add_time;
        }

        public void setPgoods_add_time(String pgoods_add_time) {
            this.pgoods_add_time = pgoods_add_time;
        }

        public String getPgoods_keywords() {
            return pgoods_keywords;
        }

        public void setPgoods_keywords(String pgoods_keywords) {
            this.pgoods_keywords = pgoods_keywords;
        }

        public String getPgoods_description() {
            return pgoods_description;
        }

        public void setPgoods_description(String pgoods_description) {
            this.pgoods_description = pgoods_description;
        }

        public String getPgoods_body() {
            return pgoods_body;
        }

        public void setPgoods_body(String pgoods_body) {
            this.pgoods_body = pgoods_body;
        }

        public String getPgoods_state() {
            return pgoods_state;
        }

        public void setPgoods_state(String pgoods_state) {
            this.pgoods_state = pgoods_state;
        }

        public String getPgoods_close_reason() {
            return pgoods_close_reason;
        }

        public void setPgoods_close_reason(String pgoods_close_reason) {
            this.pgoods_close_reason = pgoods_close_reason;
        }

        public String getPgoods_salenum() {
            return pgoods_salenum;
        }

        public void setPgoods_salenum(String pgoods_salenum) {
            this.pgoods_salenum = pgoods_salenum;
        }

        public String getPgoods_view() {
            return pgoods_view;
        }

        public void setPgoods_view(String pgoods_view) {
            this.pgoods_view = pgoods_view;
        }

        public String getPgoods_islimit() {
            return pgoods_islimit;
        }

        public void setPgoods_islimit(String pgoods_islimit) {
            this.pgoods_islimit = pgoods_islimit;
        }

        public String getPgoods_limitnum() {
            return pgoods_limitnum;
        }

        public void setPgoods_limitnum(String pgoods_limitnum) {
            this.pgoods_limitnum = pgoods_limitnum;
        }

        public String getPgoods_islimittime() {
            return pgoods_islimittime;
        }

        public void setPgoods_islimittime(String pgoods_islimittime) {
            this.pgoods_islimittime = pgoods_islimittime;
        }

        public String getPgoods_limitmgrade() {
            return pgoods_limitmgrade;
        }

        public void setPgoods_limitmgrade(String pgoods_limitmgrade) {
            this.pgoods_limitmgrade = pgoods_limitmgrade;
        }

        public String getPgoods_starttime() {
            return pgoods_starttime;
        }

        public void setPgoods_starttime(String pgoods_starttime) {
            this.pgoods_starttime = pgoods_starttime;
        }

        public String getPgoods_endtime() {
            return pgoods_endtime;
        }

        public void setPgoods_endtime(String pgoods_endtime) {
            this.pgoods_endtime = pgoods_endtime;
        }

        public String getPgoods_sort() {
            return pgoods_sort;
        }

        public void setPgoods_sort(String pgoods_sort) {
            this.pgoods_sort = pgoods_sort;
        }

        public String getPgoods_image_old() {
            return pgoods_image_old;
        }

        public void setPgoods_image_old(String pgoods_image_old) {
            this.pgoods_image_old = pgoods_image_old;
        }

        public String getPgoods_image_small() {
            return pgoods_image_small;
        }

        public void setPgoods_image_small(String pgoods_image_small) {
            this.pgoods_image_small = pgoods_image_small;
        }

        public String getEx_state() {
            return ex_state;
        }

        public void setEx_state(String ex_state) {
            this.ex_state = ex_state;
        }

        public String getPgoods_limitgradename() {
            return pgoods_limitgradename;
        }

        public void setPgoods_limitgradename(String pgoods_limitgradename) {
            this.pgoods_limitgradename = pgoods_limitgradename;
        }
    }

    public static class RedpacketBean {
        /**
         * rpacket_t_id : 2
         * rpacket_t_title : 周年庆100元红包
         * rpacket_t_desc : 周年庆百元红包
         * rpacket_t_start_date : 1475942400
         * rpacket_t_end_date : 2018-10-31
         * rpacket_t_price : 100.00
         * rpacket_t_limit : 150.00
         * rpacket_t_adminid : 1
         * rpacket_t_state : 1
         * rpacket_t_total : 10000
         * rpacket_t_giveout : 2
         * rpacket_t_used : 1
         * rpacket_t_updatetime : 1476001478
         * rpacket_t_points : 10
         * rpacket_t_eachlimit : 1
         * rpacket_t_customimg : 05293454781970862.png
         * rpacket_t_recommend : 1
         * rpacket_t_gettype : 1
         * rpacket_t_isbuild : 0
         * rpacket_t_mgradelimit : 0
         * rpacket_t_customimg_url : http://test.shopnctest.com/data/upload/shop/redpacket/05293454781970862.png
         * rpacket_t_gettype_key : points
         * rpacket_t_gettype_text : 积分兑换
         * rpacket_t_state_text : 有效
         * rpacket_t_mgradelimittext : V0
         */

        private String rpacket_t_id;
        private String rpacket_t_title;
        private String rpacket_t_desc;
        private String rpacket_t_start_date;
        private String rpacket_t_end_date;
        private String rpacket_t_price;
        private String rpacket_t_limit;
        private String rpacket_t_adminid;
        private String rpacket_t_state;
        private String rpacket_t_total;
        private String rpacket_t_giveout;
        private String rpacket_t_used;
        private String rpacket_t_updatetime;
        private String rpacket_t_points;
        private String rpacket_t_eachlimit;
        private String rpacket_t_customimg;
        private String rpacket_t_recommend;
        private String rpacket_t_gettype;
        private String rpacket_t_isbuild;
        private String rpacket_t_mgradelimit;
        private String rpacket_t_customimg_url;
        private String rpacket_t_gettype_key;
        private String rpacket_t_gettype_text;
        private String rpacket_t_state_text;
        private String rpacket_t_mgradelimittext;

        public String getRpacket_t_id() {
            return rpacket_t_id;
        }

        public void setRpacket_t_id(String rpacket_t_id) {
            this.rpacket_t_id = rpacket_t_id;
        }

        public String getRpacket_t_title() {
            return rpacket_t_title;
        }

        public void setRpacket_t_title(String rpacket_t_title) {
            this.rpacket_t_title = rpacket_t_title;
        }

        public String getRpacket_t_desc() {
            return rpacket_t_desc;
        }

        public void setRpacket_t_desc(String rpacket_t_desc) {
            this.rpacket_t_desc = rpacket_t_desc;
        }

        public String getRpacket_t_start_date() {
            return rpacket_t_start_date;
        }

        public void setRpacket_t_start_date(String rpacket_t_start_date) {
            this.rpacket_t_start_date = rpacket_t_start_date;
        }

        public String getRpacket_t_end_date() {
            return rpacket_t_end_date;
        }

        public void setRpacket_t_end_date(String rpacket_t_end_date) {
            this.rpacket_t_end_date = rpacket_t_end_date;
        }

        public String getRpacket_t_price() {
            return rpacket_t_price;
        }

        public void setRpacket_t_price(String rpacket_t_price) {
            this.rpacket_t_price = rpacket_t_price;
        }

        public String getRpacket_t_limit() {
            return rpacket_t_limit;
        }

        public void setRpacket_t_limit(String rpacket_t_limit) {
            this.rpacket_t_limit = rpacket_t_limit;
        }

        public String getRpacket_t_adminid() {
            return rpacket_t_adminid;
        }

        public void setRpacket_t_adminid(String rpacket_t_adminid) {
            this.rpacket_t_adminid = rpacket_t_adminid;
        }

        public String getRpacket_t_state() {
            return rpacket_t_state;
        }

        public void setRpacket_t_state(String rpacket_t_state) {
            this.rpacket_t_state = rpacket_t_state;
        }

        public String getRpacket_t_total() {
            return rpacket_t_total;
        }

        public void setRpacket_t_total(String rpacket_t_total) {
            this.rpacket_t_total = rpacket_t_total;
        }

        public String getRpacket_t_giveout() {
            return rpacket_t_giveout;
        }

        public void setRpacket_t_giveout(String rpacket_t_giveout) {
            this.rpacket_t_giveout = rpacket_t_giveout;
        }

        public String getRpacket_t_used() {
            return rpacket_t_used;
        }

        public void setRpacket_t_used(String rpacket_t_used) {
            this.rpacket_t_used = rpacket_t_used;
        }

        public String getRpacket_t_updatetime() {
            return rpacket_t_updatetime;
        }

        public void setRpacket_t_updatetime(String rpacket_t_updatetime) {
            this.rpacket_t_updatetime = rpacket_t_updatetime;
        }

        public String getRpacket_t_points() {
            return rpacket_t_points;
        }

        public void setRpacket_t_points(String rpacket_t_points) {
            this.rpacket_t_points = rpacket_t_points;
        }

        public String getRpacket_t_eachlimit() {
            return rpacket_t_eachlimit;
        }

        public void setRpacket_t_eachlimit(String rpacket_t_eachlimit) {
            this.rpacket_t_eachlimit = rpacket_t_eachlimit;
        }

        public String getRpacket_t_customimg() {
            return rpacket_t_customimg;
        }

        public void setRpacket_t_customimg(String rpacket_t_customimg) {
            this.rpacket_t_customimg = rpacket_t_customimg;
        }

        public String getRpacket_t_recommend() {
            return rpacket_t_recommend;
        }

        public void setRpacket_t_recommend(String rpacket_t_recommend) {
            this.rpacket_t_recommend = rpacket_t_recommend;
        }

        public String getRpacket_t_gettype() {
            return rpacket_t_gettype;
        }

        public void setRpacket_t_gettype(String rpacket_t_gettype) {
            this.rpacket_t_gettype = rpacket_t_gettype;
        }

        public String getRpacket_t_isbuild() {
            return rpacket_t_isbuild;
        }

        public void setRpacket_t_isbuild(String rpacket_t_isbuild) {
            this.rpacket_t_isbuild = rpacket_t_isbuild;
        }

        public String getRpacket_t_mgradelimit() {
            return rpacket_t_mgradelimit;
        }

        public void setRpacket_t_mgradelimit(String rpacket_t_mgradelimit) {
            this.rpacket_t_mgradelimit = rpacket_t_mgradelimit;
        }

        public String getRpacket_t_customimg_url() {
            return rpacket_t_customimg_url;
        }

        public void setRpacket_t_customimg_url(String rpacket_t_customimg_url) {
            this.rpacket_t_customimg_url = rpacket_t_customimg_url;
        }

        public String getRpacket_t_gettype_key() {
            return rpacket_t_gettype_key;
        }

        public void setRpacket_t_gettype_key(String rpacket_t_gettype_key) {
            this.rpacket_t_gettype_key = rpacket_t_gettype_key;
        }

        public String getRpacket_t_gettype_text() {
            return rpacket_t_gettype_text;
        }

        public void setRpacket_t_gettype_text(String rpacket_t_gettype_text) {
            this.rpacket_t_gettype_text = rpacket_t_gettype_text;
        }

        public String getRpacket_t_state_text() {
            return rpacket_t_state_text;
        }

        public void setRpacket_t_state_text(String rpacket_t_state_text) {
            this.rpacket_t_state_text = rpacket_t_state_text;
        }

        public String getRpacket_t_mgradelimittext() {
            return rpacket_t_mgradelimittext;
        }

        public void setRpacket_t_mgradelimittext(String rpacket_t_mgradelimittext) {
            this.rpacket_t_mgradelimittext = rpacket_t_mgradelimittext;
        }
    }
}
