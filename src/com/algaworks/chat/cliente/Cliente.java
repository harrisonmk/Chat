/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.chat.cliente;

/**
 *
 * @author Harrison
 */
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.algaworks.chat.cliente.gui.JanelaGui;
import com.algaworks.chat.cliente.thread.RecebeMensagemServidor;
import java.io.IOException;

public class Cliente extends JanelaGui {

	private Socket socket;
	
	public static void main(String[] args) {
            Cliente cliente = new Cliente();
	}

	@Override
	protected boolean conectar() {
		System.out.println("Conectando no servidor...");
		try {
			this.socket = new Socket("127.0.0.1", 12345);
			
			RecebeMensagemServidor recebeMensagemServidor = new RecebeMensagemServidor(this.socket, this);
			new Thread(recebeMensagemServidor).start();
			
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void sendMessage(String mensagem) {
		System.out.println("Envia a mensagem via socket para o servidor - " + mensagem);
		
		try {
			OutputStream os = this.socket.getOutputStream();
			DataOutput dos = new DataOutputStream(os);
			dos.writeUTF(mensagem);
		} catch (IOException e) {
		}
	}
	
}