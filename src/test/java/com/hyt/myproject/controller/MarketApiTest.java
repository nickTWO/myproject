//package com.hyt.myproject.controller;
//
//import com.btcpay.common.dto.CurrencyType;
//import com.btcpay.common.dto.GlobalMarketDto;
//import com.btcpay.common.utils.JsonUtils;
//import com.btcpay.common.utils.SignUtils;
//import com.btcpay.common.vo.request.MarketRequest;
//import com.btcpay.wallet.SpringRestDocApplicationTests;
//import org.joda.time.DateTime;
//import org.junit.Test;
//import org.springframework.http.MediaType;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Created by Barry on 2018/2/1.
// */
//public class MarketApiTest extends SpringRestDocApplicationTests {
//
//    /**
//     * 校验短信验证码
//     * author: lhx
//     * {@link com.btcpay.wallet.api.MarketApi#getMarketPageList(HttpServletRequest, MarketRequest)} (HttpServletRequest, MarketRequest)}
//     */
//    @Test
//    public void testGetMarketPageList() throws Exception {
//        MarketRequest marketRequest = new MarketRequest();
//        marketRequest.setCurrencyType(CurrencyType.BTC);
//        marketRequest.setPageNum("1");
//        marketRequest.setStatus(GlobalMarketDto.CurrencyStatus.OPEN);
//        marketRequest.setPlatform("");
//        marketRequest.setSignType("MD5");
//        marketRequest.setTimestamp(new DateTime());
//        marketRequest.setSign(SignUtils.createMd5Sign("BBBBAE87057FBC7E7ED68CBF329CB3E9", marketRequest));
//
//        this.mockMvc.perform(post("/api/v1/market/list")
//                .header("Authorization",getAuthorization("echo@qq.com"))
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(JsonUtils.objectToJson(marketRequest)).accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("restful-user-getMarketPageList", requestFields(
//                        fieldWithPath("currencyType").type("String").description("币种：BTC, USDT, ETH, LTC"),
//                        fieldWithPath("status").type("String").optional().description("状态，OPEN：开启,CLOSE：关闭"),
//                        fieldWithPath("pageNum").type("String").description("页码"),
//                        fieldWithPath("platform").type("String").optional().description("平台"),
//                        fieldWithPath("signType").type("String").optional().description("签名方式"),
//                        fieldWithPath("timestamp").type("String").optional().description("时间戳"),
//                        fieldWithPath("sign").type("String").description("签名串")
//                        ),
//                        relaxedResponseFields(
//                                fieldWithPath("errCode").type("String").description("错误码"),
//                                fieldWithPath("errMessage").type("String").description("错误信息"),
//                                fieldWithPath("resp_data").type("String").description("相应数据"))));
//    }
//
//
//}
