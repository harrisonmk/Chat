/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.chat.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.chat.servidor.thread.RecebeMensagemCliente;

public class Servidor {
	
	private final List<RecebeMensagemCliente> clientes = new ArrayList<>();

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.aguardarConexoes();
	}
	
	public void aguardarConexoes() {
		try (ServerSocket server = new ServerSocket(12345)) {
			
			while (true) {
				System.out.println("Aguardando conexões...");
				Socket socket = server.accept();
				
				RecebeMensagemCliente recebeMensagemCliente = new RecebeMensagemCliente(socket, this);
				new Thread(recebeMensagemCliente).start();
				
				this.clientes.add(recebeMensagemCliente);
				System.out.println("Novo cliente conectado.");
			}
		} catch (Exception e) {
		}
	}
	
	public void enviarMensagensClientes(String mensagem) {
            this.clientes.forEach((cliente) -> {
                cliente.enviarMensagem(mensagem);
            });
	}
	
}