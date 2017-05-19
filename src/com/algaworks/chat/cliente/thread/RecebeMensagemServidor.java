/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.chat.cliente.thread;

/**
 *
 * @author Harrison
 */
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

import com.algaworks.chat.cliente.gui.JanelaGui;
import java.io.IOException;

public class RecebeMensagemServidor implements Runnable {

	private final Socket socket;
	private final JanelaGui janela;

	public RecebeMensagemServidor(Socket socket, JanelaGui janela) {
		this.socket = socket;
		this.janela = janela;
	}

	@Override
	public void run() {
		while (true) {
			try {
				InputStream is = this.socket.getInputStream();
				DataInput dis = new DataInputStream(is);
				String msgRecebida = dis.readUTF();
				
				janela.adicionaMensagem(msgRecebida);
			} catch (IOException e) {
			}
		}
	}

}