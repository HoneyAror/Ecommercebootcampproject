//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//
//enum fromStatus {
//        ORDER_PLACED, CANCELLED, ORDER_REJECTED, ORDER_CONFIRMED, ORDER_SHIPPED, DELIVERED, RETURN_REQUESTED, RETURN_REJECTED, RETURN_APPROVED, PICK_UP_INITIATED, PICK_UP_COMPLETED, REFUND_INITIATED, REFUND_COMPLETED;
//    }
//
//    enum toStatus {
//        CANCELLED, ORDER_CONFIRMED, ORDER_REJECTED, REFUND_INITIATED, CLOSED, ORDER_SHIPPED, DELIVERED, RETURN_REQUESTED, RETURN_REJECTED, RETURN_APPROVED, PICK_UP_INITIATED, PICK_UP_COMPLETED, REFUND_COMPLETED;
//    }
//
//    @Entity
//    public class OrderStatus {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @OneToOne
//        @MapsId
//        private OrderProduct orderProduct;
//
//        @Enumerated(EnumType.STRING)
//        private fromStatus fromStatus;
//
//        @Enumerated(EnumType.STRING)
//        private toStatus toStatus;
//
//        private String transitionNotesComments;
//}
