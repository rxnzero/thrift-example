package com.dhlee.thrift.arithmetic;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.dhlee.thrift.arithmetic.gen.ArithmeticService;

public class ArithmeticClient {
	
	private void invoke() {
		TTransport transport;
		try {
			transport = new TSocket("localhost", 7911);
			TProtocol protocol = new TBinaryProtocol(transport);
			ArithmeticService.Client client = new ArithmeticService.Client(protocol);
			transport.open();
			
			long addResult = client.add(100, 200);
			System.out.println("Add result: " + addResult);
			long multiplyResult = client.multiply(20, 40);
			System.out.println("Multiply result: " + multiplyResult);
			transport.close();
		}
		catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();			
		}
	}
	
	public static void main(String[] args) {
		ArithmeticClient client = new ArithmeticClient();
		client.invoke();
	}
	
}
