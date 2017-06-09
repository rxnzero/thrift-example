package com.dhlee.thrift.arithmetic;

import org.apache.thrift.TException;

import com.dhlee.thrift.arithmetic.gen.ArithmeticService;

public class ArithmeticServiceImpl implements ArithmeticService.Iface {

	@Override
	public long add(int num1, int num2) throws TException {
		return num1 + num2;
	}

	@Override
	public long multiply(int num1, int num2) throws TException {
		return num1 * num2;
	}

}
