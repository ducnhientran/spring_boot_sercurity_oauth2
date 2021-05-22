//package com.study.ecommerce.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@PreAuthorize("hasPermission('USER', 'CREATE')")
//public class HomeController {
//
//    @GetMapping("/home")
//    //@PreAuthorize("hasPermission('[USER,ADMIN]', '[CREATE]')")
//    public ResponseEntity<String> home(){
//       return ResponseEntity.ok("Hello Spring");
//    }
//
//
//    @GetMapping("/hometest")
//    //@PreAuthorize("hasPermission('USER','CREAT')")
//    public ResponseEntity<String> hometest(){
//        return ResponseEntity.ok("Hello Spring");
//    }
//
//
//    @GetMapping("/hometest1")
//    //@PreAuthorize("hasPermission('USER1','')")
//    public ResponseEntity<String> hometest1(){
//        return ResponseEntity.ok("Hello Spring");
//    }
//}
