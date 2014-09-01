package com.example.testsocketio1;

import java.net.URISyntaxException;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Socket socket;
		try {
			socket = IO.socket("http://172.16.23.103:3000/");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

				  @Override
				  public void call(Object... args) {
				    socket.emit("add user", "android");
				    socket.emit("new message", "hello i am android");
				  }

				}).on("new message", new Emitter.Listener() {

				  @Override
				  public void call(Object... args) {
					  System.out.println(args.toString());
				  }

				}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

				  @Override
				  public void call(Object... args) {}

				});
				socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
