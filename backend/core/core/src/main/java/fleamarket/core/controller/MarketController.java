package fleamarket.core.controller;

import fleamarket.core.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;

    //마켓 등록
    @PostMapping("/market/add")
    public String addNewMarket(@RequestParam double latitude,@RequestParam double longitude,HttpServletRequest request){
        return marketService.marektAdd(latitude,longitude,request);
    }

    //특정 마켓의 아이템 리스트 반환
    @GetMapping("/market")
    public Object marketInfo(@RequestParam Long id,HttpServletRequest request){
        return marketService.getMarketInfo(id,request);
    }

    //특정 마켓에 아이템 등록
    @PostMapping("/market/save")
    public String itemSave(@RequestParam Long marketId, @RequestParam String itemName, @RequestParam Long price, @RequestParam int sellingTime, @RequestParam MultipartFile photo,@RequestParam String description, HttpServletRequest request) throws IOException {
        return marketService.saveItem(marketId,itemName,price,sellingTime,photo,description,request);
    }

    //모든 마켓 정보 반환
    @GetMapping("/map")
    public Object marketLocationInfo(@RequestParam("lat") double latitude,@RequestParam("lng") double longitude,HttpServletRequest request){
        return marketService.getMap(latitude,longitude,request);
    }

    @PostMapping("/market/reserve") // 찜하기 버튼
    public String reserveItem(@RequestParam Long itemId,HttpServletRequest request){
        return marketService.reserveMember(itemId,request);
    }

    @PostMapping("/market/Confirm") //예약자들중 누구와 거래할지 결정 버튼
    public String reserveConfirm(@RequestParam Long itemId,@RequestParam Long memberId, HttpServletRequest request) {
        return marketService.reserveConfirm(itemId,memberId,request);
    }

    @PostMapping("/market/soldout") //거래완료 버튼
    public void sellItem(@RequestParam Long itemId,@RequestParam Long memberId, HttpServletRequest request){
        marketService.soldoutItem(itemId,memberId,request);
    }
}
