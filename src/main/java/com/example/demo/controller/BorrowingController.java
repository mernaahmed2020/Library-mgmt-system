package com.example.demo.controller;

import com.example.demo.model.entity.Borrowing;
import com.example.demo.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;


}
