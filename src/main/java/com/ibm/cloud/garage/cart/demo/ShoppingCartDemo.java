package com.ibm.cloud.garage.cart.demo;

import java.io.FileNotFoundException;

import com.ibm.cloud.garage.cart.service.ShoppingCartService;

public class ShoppingCartDemo {

	public static void main(String[] args) {
		String fileName = "SpaceDelimiterInput";
		ShoppingCartService cartService = new ShoppingCartService();
		try {
			System.out.println("Output 1:");
			System.out.println(cartService.buildCart(fileName));
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("Problem occured while processing space delimeter file");
		}

		fileName = "PipeDelimiterInput";
		try {
			System.out.println();
			System.out.println("Output 2:");
			System.out.println(cartService.buildCart(fileName));
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("Problem occured while processing pipe delimeter file");
		}

		fileName = "CommaDelimiterInput";
		try {
			System.out.println();
			System.out.println("Output 3:");
			System.out.println(cartService.buildCart(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Problem occured while processing comma delimeter file");
		}
	}

}
