package Bomb;

import java.io.File;
import java.util.ArrayList;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import lejos.nxt.*;
import lejos.util.Timer;
import lejos.util.TimerListener;
import Networking.*;
//import Units.BombTest.controlClaw;




public class display_timer implements MessageListenerInterface{

	protected static final int CONST_SEQUENCE_LENGTH = 10; // number of colors, sequence length
	
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
	protected Motor m_claw;
	protected TouchSensor m_defusableSensor;
	
	protected Timer m_countdownTimer;
	
	protected MessageFramework m_messageFrameWork;
	
	protected String m_currentSequence;
	protected int m_pointer;
	
	protected ArrayList<Image> m_images;
	protected Image m_colonn;
	
	protected int m_gameTime;
	//protected int m_startBombTime;
	//protected int m_remainingBombTime;

	protected int m_minutes;
	protected int m_seconds1;
	protected int m_seconds2;
	
	protected boolean m_beepEnabled = false;
	protected Graphics m_g;
	
	protected boolean m_planted = false;
	
	display_timer(int bombTime) {
		
		m_g = new Graphics();
		m_countdownTimer = new Timer(1000, new TimerListener() {
		
			@Override
			public void timedOut() {
				countDownTimer();
			}
		});
		
		m_messageFrameWork = MessageFramework.getInstance();
		m_messageFrameWork.addMessageListener(this);
		m_messageFrameWork.StartListen();
		
		m_claw = new Motor(MotorPort.A);
		m_defusableSensor = new TouchSensor(SensorPort.S1);
		
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
		
		m_gameTime = -1;
		
		//setBombTime(bombTime);
	}
	
//	public void setBombTime(int seconds) {
//		m_startBombTime = seconds;
//		m_remainingBombTime = seconds;
//	}
	public void countDownTimer() {
		CalculateImageNumbers(m_gameTime);
		RefreshLCD();
		
		m_gameTime--;
	}
	
	public void setNewTime(int seconds)
	{
		m_gameTime = seconds;
	}
	
	public void startCountdown() {
		
		//STATE: Waiting for game start
		m_g.drawString("Waiting for game to start", 1, 20, false);
		while (m_gameTime == -1) {
		}	 

		//STATE: Waiting for planted
		m_g.clear();
		m_countdownTimer.start();
		while(!m_planted && m_gameTime > 0){
		}
		
		
		
		//STATE: Waiting for defused;
//		for (int j = 0; j <=m_gameTime; j++){	
			//m_g.drawString("time left:"+(m_gameTime-j), 1, 20, false);
//			 try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					LCD.drawString(e.getMessage(), 0, 0);
//				}
			
//			if(m_planted){
				//toExplosion();
//				break;
//			}
//			else if (j == m_gameTime)
//				timeOut();
//		}
		
		// when bomb lays on the brick, sensor is on
		 
		 
		 while (!m_defusableSensor.isPressed());
		 
		 Sound.beep();
		 
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LCD.drawString(e.getMessage(), 0, 0);
		}	
	}
	
	private void timeOut() {
		m_messageFrameWork.SendMessage(new LIMessage(LIMessageType.Command, "TO"));
		
	}

//	private void toExplosion() {
//		
//		generateSeq();
//		m_g.clear();
//		
//		while(m_gameTime >= 0) {
//			
//			CalculateImageNumbers(m_gameTime);
//			RefreshLCD();
//			
//			m_g.drawString("seq.:"+m_currentSequence, 1, 56, false);
//			if(m_beepEnabled)
//				Sound.beep();
//			
//			if (m_gameTime == 0)
//				Explode();
//			
//			try {
//				Thread.sleep(1000); //Sleep for 1 second before next countdown.
//			} catch (InterruptedException e) {
//				LCD.drawString(e.getMessage(), 0, 0);
//			}
//		 }
//	}

	private boolean checkSequence(char theColor2) {
		
		m_g.drawString("pressed SEQ: " + theColor2, 1, 40);
		m_g.drawString("seq: " + m_currentSequence.charAt(m_pointer), 1, 48);
		if(m_currentSequence.charAt(m_pointer)!=theColor2){
			
			Sound.buzz();
			generateSeq();
			return false;
		}
			else {
				
				m_pointer ++;
				if(m_pointer == CONST_SEQUENCE_LENGTH){
					Sound.twoBeeps();
					return true;
				}
				
			} 
						
		return false;
	}

	private void generateSeq() {
		
		int ran;
		
		StringBuilder sb = new StringBuilder();
		
		
		for (int i=1; i<=CONST_SEQUENCE_LENGTH;i++){
		
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
		
		m_currentSequence = sb.toString();
		// for debugging
		
		m_g.drawString("Seqence: " + m_currentSequence, 1, 56);
		
		// Sending Random sequence to CT 
		m_messageFrameWork.SendMessage(new LIMessage(LIMessageType.Command, "DS"+m_currentSequence));	
	}

	private void CalculateImageNumbers(int remainingGameTime) {

		int remainingSeconds = remainingGameTime % 60;
		
		m_minutes = (remainingGameTime - remainingSeconds) / 60;
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
		m_g.drawString("Remaining: " + m_gameTime, 1, 40);
		//m_g.drawString("Start: " + m_startBombTime, 1, 48);
	}
	
	public void setBeepEnabled(boolean enabled)
	{
		m_beepEnabled = enabled;
	}
// ############################################ MESSAGE FAILURE
	protected void Explode() {
		Sound.buzz();
		m_messageFrameWork.SendMessage(new LIMessage(LIMessageType.Command, "BOOM"));		
	}
// ############################################ MESSAGE SUCCESS 	
	public void defused() {
		Sound.beepSequenceUp();
		m_messageFrameWork.SendMessage(new LIMessage(LIMessageType.Command, "defused"));
	}
	
	private void planted() {
		m_planted = true;
		m_claw.rotate(60);
	}
	
	
	
// ############################ RECEIVING MESSAGES HERE #########################
	@Override
	public void recievedNewMessage(LIMessage msg) {
		
		String cmd = msg.getPayload().substring(0, 2);
		m_g.drawString("message: " + cmd, 1, 48);
		
		if(cmd.equals("GT")){
						
			String gt = msg.getPayload().substring(2, 6);
			Sound.beepSequenceUp();
			m_gameTime = Integer.parseInt(gt);
			m_g.clear();
		}
		
		else if(cmd.equals("PL")){ //trigger to detach the bomb from the terrorist unit
			
			planted();
			m_messageFrameWork.SendMessage(new LIMessage(LIMessageType.Command, "Bomb deployed"));
		}
		
		else if(cmd.equals("SC")){ // color cable to cut
			
			char nextWireToCut = msg.getPayload().charAt(2); // code sequence received from CT
			
			m_g.drawString("SCx: " + nextWireToCut, 1, 54);
				
			if (checkSequence(nextWireToCut)){
				defused();
			}
		}
	}
}
