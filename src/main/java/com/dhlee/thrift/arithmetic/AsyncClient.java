package com.dhlee.thrift.arithmetic;

import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;

import com.dhlee.thrift.arithmetic.gen.ArithmeticService;

public class AsyncClient {
	
	public static void main(String[] args) {
		AsyncClient client = new AsyncClient();
		client.invoke();
	}
	
	private void invoke() {
		try {
			TNonblockingSocket socket = new TNonblockingSocket("localhost", 7911);
			TAsyncClientManager manager = new TAsyncClientManager();
			
			ArithmeticService.AsyncClient client = new ArithmeticService.
					AsyncClient(new TBinaryProtocol.Factory(), manager,
							socket); 
			client.add(200, 400, new AddMethodCallback());
			
			client = new ArithmeticService.
					AsyncClient(new TBinaryProtocol.Factory(), new TAsyncClientManager(),
							new TNonblockingSocket("localhost", 7911)); 
			client.multiply(20, 50, new MultiplyMethodCallback());
			
			System.out.println("Sleep 2 secs...");
			
			Thread.sleep(2 *1000);
			System.out.println("Sleep 10secs end");		
		}
		catch(TTransportException e) {
			e.printStackTrace();
		}
		catch(TException  e) {
			e.printStackTrace();
		}
		catch(IOException  e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AddMethodCallback implements AsyncMethodCallback<Long> {
		@Override
		public void onError(Exception e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}

		@Override
		public void onComplete(Long result) {
			System.out.println("Add from server: " + result);
		}
		
	}
	
	class MultiplyMethodCallback implements AsyncMethodCallback<Long> {
		@Override
		public void onComplete(Long result) {
			System.out.println("Multiply from server: " + result);			
		}

		@Override
		public void onError(Exception e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
