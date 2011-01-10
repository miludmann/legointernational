package Bomb;

import java.io.File;
import java.util.ArrayList;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import lejos.nxt.*;
import Networking.*;
//import Units.BombTest.controlClaw;




public class display_timer implements MessageListenerInterface{
	
	// Bomb font didits saved in binary format
	private static byte[] one = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xf0, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, };
	private static byte[] zero = new byte[] {(byte) 0x00, (byte) 0x80, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7c, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x07, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xc0, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xc0, (byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, }; 
	private static byte[] two = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x7e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x07, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xc1, (byte) 0xe3, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xc0, (byte) 0xe0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xf8, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x3f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xe0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x3f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x03, (byte) 0x81, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xc0, (byte) 0x80, (byte) 0x00, (byte) 0x3c, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x3e, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, };
	private static byte[] three = new byte[] {(byte) 0x80, (byte) 0xc0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x7c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0xc0, (byte) 0x00, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x03, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x03, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7c, (byte) 0x7c, (byte) 0x7c, (byte) 0x7c, (byte) 0x7c, (byte) 0x7e, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xe7, (byte) 0xc3, (byte) 0x01, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xc0, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x80, (byte) 0xc1, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x01, }; 
	private static byte[] four = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xc0, (byte) 0xe0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0xfc, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf7, (byte) 0xf3, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, };
	private static byte[] five = new byte[] {(byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x7c, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xc0, (byte) 0xc0, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xfc, (byte) 0xf0, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xe0, (byte) 0xc0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xc0, (byte) 0xe3, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x01, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x00, }; 
	private static byte[] six = new byte[] {(byte) 0x80, (byte) 0xc0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0xc0, (byte) 0x80, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe1, (byte) 0xe3, (byte) 0xc7, (byte) 0xcf, (byte) 0x8f, (byte) 0x8f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xf8, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xe0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x01, };
	private static byte[] seven = new byte[] {(byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x7e, (byte) 0x1e, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xf8, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x3e, (byte) 0x06, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x0f, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xf0, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xf0, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x70, (byte) 0x3c, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }; 
	private static byte[] eight = new byte[] {(byte) 0x00, (byte) 0x80, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0x7e, (byte) 0x7e, (byte) 0x7e, (byte) 0x3c, (byte) 0x00, (byte) 0x00, (byte) 0x3e, (byte) 0x7e, (byte) 0x7e, (byte) 0x7e, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0x80, (byte) 0x7c, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xc0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xc1, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x80, (byte) 0xe0, (byte) 0xf1, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x00, (byte) 0x00, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf1, (byte) 0xe0, (byte) 0x3f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x01, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3e, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f, (byte) 0x0f, (byte) 0x07, (byte) 0x03, (byte) 0x00, };
	private static byte[] nine = new byte[] {(byte) 0x80, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0x80, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfb, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0x07, (byte) 0x1f, (byte) 0x3f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf1, (byte) 0xf1, (byte) 0xc1, (byte) 0xc1, (byte) 0x81, (byte) 0x81, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x80, (byte) 0x80, (byte) 0xc0, (byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0x00, (byte) 0x01, (byte) 0x07, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f, (byte) 0x07, (byte) 0x07, (byte) 0x01, (byte) 0x00, (byte) 0x00, }; 
	private static byte[] colon = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1e, (byte) 0x1e, (byte) 0x1e, (byte) 0x1e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3c, (byte) 0x3c, (byte) 0x3c, (byte) 0x3c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, };
	private static byte[] empty = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, };
	
	
//	private Motor redLight = new Motor(MotorPort.C);
//	private Motor greenLight = new Motor(MotorPort.B);	
	private Motor claw_m = new Motor(MotorPort.A);

	protected BluetoothCommander m_btc;
	
	protected static File danger;
	
	protected static int min = 1; // range for sequence to generate
	protected static int max = 4; // plus min
	
	protected String theSequence;
	protected char theColor;
	protected int pointer;
	
	protected ArrayList<Image> m_images;
	
	protected Image m_colonn;
	
	int numberOfColors; // number of colors
	
	protected int m_startBombTime;
	protected int m_remainingBombTime;

	protected int m_minutes;
	protected int m_seconds1;
	protected int m_seconds2;
	
	protected boolean m_beepEnabled = false;
	protected Graphics m_g;
	
	protected boolean planted = false;
	
	protected TouchSensor m_theBomb;
	
	protected int m_gameTime;
	
	display_timer(int bombTime) {
		
		m_g = new Graphics();
		
		m_btc = new BluetoothCommander();
		m_btc.addMessageListener(this);
		m_btc.StartListen();
		
		m_theBomb = new TouchSensor(SensorPort.S1);
		
		m_images = new ArrayList<Image>();
		
		m_images.add(new Image(29, 39, zero));
		
		m_images.add(new Image (29, 39, one));
		m_images.add(new Image (29, 39, two));
		m_images.add(new Image (29, 39, three));
		m_images.add(new Image (29, 39, four));
		m_images.add(new Image (29, 39, five));
		m_images.add(new Image (29, 39, six));
		m_images.add(new Image (29, 39, seven));
		m_images.add(new Image (29, 39, eight));
		m_images.add(new Image (29, 39, nine));
		m_images.add(new Image (29, 39, empty));
		
		
		
		m_colonn = new Image (4, 39, colon);
		
		setBombTime(bombTime);
	}
	
	public void setBombTime(int seconds) {
		m_startBombTime = seconds;
		m_remainingBombTime = seconds;
	}
	
	public void startCountdown() {
		
		while (m_gameTime == 0) {
			m_g.drawString("Waiting for game to start", 1, 20, false);
	}	 
		
		m_g.clear();
		for (int j = 0; j <=m_gameTime; j++){	
			m_g.drawString("time left:"+(m_gameTime-j), 1, 20, false);
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					LCD.drawString(e.getMessage(), 0, 0);
				}
			
			if(planted){
			
			toExplosion();
			break;
			}
			else if (j == m_gameTime)
				timeOut();
		}
		
		// when bomb lays on the brick, sensor is on
		 
		 
		 while (!m_theBomb.isPressed());
		 
		 Sound.beep();
		 
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LCD.drawString(e.getMessage(), 0, 0);
		}	
	}
	
	private void timeOut() {
		m_btc.SendMessage(new LIMessage(LIMessageType.Command, "TO"));
		
	}

	private void toExplosion() {
		
		generateSeq();
		m_g.clear();
		for (int i = 0; i <= m_startBombTime ; i++) {
			
			CalculateImageNumbers(i);
			RefreshLCD();
			m_g.drawString("seq.:"+theSequence, 1, 56, false);
			if(m_beepEnabled)
				Sound.beep();
			
			if (i==m_startBombTime)
				Explode();
			
			try {
				Thread.sleep(1000); //Sleep for 1 second before next countdown.
			} catch (InterruptedException e) {
				LCD.drawString(e.getMessage(), 0, 0);
			}
			
			
		 }
	}

	private boolean checkSequence(char theColor2) {
		
		m_g.drawString("pressed SEQ: " + theColor2, 1, 40);
		m_g.drawString("seq: " + theSequence.charAt(pointer), 1, 48);
		if(theSequence.charAt(pointer)!=theColor2){
			
			Sound.buzz();
			generateSeq();
			return false;
		}
			else {
				
				pointer ++;
				if(pointer == numberOfColors){
					Sound.twoBeeps();
					return true;
				}
				
			} 
						
		return false;
	}

	private void generateSeq() {
		
		numberOfColors = (int) (Math.round((Math.random() * max)) + min);
		int ran;
		
		StringBuilder sb = new StringBuilder();
		
		
		for (int i=1; i<=numberOfColors;i++){
		
			ran = (int) (Math.round(Math.random() * 3))+1;
			
			switch (ran){
			case 4:
				sb.append("r");
				break;
				
			case 3:
				sb.append("g");
				break;
				
			case 2:
				sb.append("y");
				break;
				
			case 1:
				sb.append("b");
				break;			
			}					
		}
		
		theSequence = sb.toString();
		// for debugging
		
		m_g.drawString("Seqence: " + theSequence, 1, 56);
		
		// Sending Random sequence to CT 
		m_btc.SendMessage(new LIMessage(LIMessageType.Command, "DS"+theSequence));	
	}

	private void CalculateImageNumbers(int secondsPassed) {

		int remainingSeconds;
		m_remainingBombTime = m_startBombTime - secondsPassed;
		remainingSeconds = m_remainingBombTime % 60;
		
		m_minutes = (m_remainingBombTime - remainingSeconds) / 60;
		m_seconds1 = (int)Math.floor((remainingSeconds / 10)); //Calculating the 1st digit of the seconds 27s /10 = 2,7 Floor = 2
		m_seconds2 = (int)Math.round( (  ((double)remainingSeconds / 10d) - (double)m_seconds1) * 10d); //Calculating 2nd digit, 27/10 - 2 (seconds1) = 0,7 x 10 = 7
	}

	private void RefreshLCD() {
		
		Image imgMin = m_images.get(m_minutes);
		Image imgSec1 = m_images.get(m_seconds1);
		Image imgSec2 = m_images.get(m_seconds2);
	
		//m_g.clear();
		m_g.drawImage(imgMin, 0, 0, false);
		m_g.drawImage(m_colonn,32,0, false);
		m_g.drawImage(imgSec1, 38, 0, false);
		m_g.drawImage(imgSec2, 70, 0, false);
		
		//debugging
		m_g.drawString("Remaining: " + m_remainingBombTime, 1, 40);
		//m_g.drawString("Start: " + m_startBombTime, 1, 48);
	}
	
	public void setBeepEnabled(boolean enabled)
	{
		m_beepEnabled = enabled;
	}
// ############################################ MESSAGE FAILURE
	protected void Explode() {
		Sound.buzz();
		m_btc.SendMessage(new LIMessage(LIMessageType.Command, "BOOM"));		
	}
// ############################################ MESSAGE SUCCESS 	
	public void defused() {
		Sound.beepSequenceUp();
		m_btc.SendMessage(new LIMessage(LIMessageType.Command, "defused"));
	}
	
	private void planted() {
		planted = true;
		claw_m.rotate(60);
	}
	
	
	
// ############################ RECEIVING MESSAGES HERE #########################
	@Override
	public void recievedNewMessage(LIMessage msg) {
		
		String cmd = msg.m_payload.substring(0, 2);
		m_g.drawString("message: " + cmd, 1, 48);
		
		if(cmd.equals("GT")){
						
			String gt = msg.m_payload.substring(2, 6);
			Sound.beepSequenceUp();
			m_gameTime = Integer.parseInt(gt);
			m_g.clear();
		}
		
		else if(cmd.equals("PL")){ //trigger to detach the bomb from the terrorist unit
			
			planted();
			m_btc.SendMessage(new LIMessage(LIMessageType.Command, "Bomb deployed"));
		}
		
		else if(cmd.equals("SC")){ // color cable to cut
			
			char dc = msg.m_payload.charAt(2); // code sequence received from CT
			theColor = dc;
			
			m_g.drawString("SCx: " + theColor, 1, 54);
				
			if (checkSequence(theColor)){
				defused();
			}
		}
	}
}
