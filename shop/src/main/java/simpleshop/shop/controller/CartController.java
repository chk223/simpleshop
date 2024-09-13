package simpleshop.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.service.ItemService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final ItemService itemService;
    //유저 장바구니 조회

    //장바구니에 아이템 추가
    //수정(삭제)
}
