package Bomb;

import java.io.File;
import java.util.ArrayList;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import lejos.nxt.*;





public class display_timer {
	
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
	

	protected static File danger;
	public static int bombTime = 530; // seconds received from host #####
	
	static int seconds;
	static int minutes;
	int sec;// = seconds;
	int min;// = minutes;
	
	

	public void final_countdown(){}
	
public static void main(String [] options) throws Exception{
	
	
		
	
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(new Image(29, 39, zero));
		
		images.add(new Image (29, 39, one));
		images.add(new Image (29, 39, two));
		images.add(new Image (29, 39, three));
		images.add(new Image (29, 39, four));
		images.add(new Image (29, 39, five));
		images.add(new Image (29, 39, six));
		images.add(new Image (29, 39, seven));
		images.add(new Image (29, 39, eight));
		images.add(new Image (29, 39, nine));
		images.add(new Image (29, 39, empty));
		Image colonn = new Image (4, 39, colon);
		
		
		
			
		
		while (!Button.ESCAPE.isPressed()) {
		 
		 Graphics g = new Graphics();
		
		 int counter;
		 double seconds1;
		 double seconds2;
		 double sec11;

	for (int i = 0; i<=bombTime ; i++){
			
			
		counter = bombTime - i;
		seconds = counter % 60;
		minutes = (counter - seconds) / 60;
		
		sec11 = ((double)seconds / 10d);
		seconds1 = ((double)seconds / 10d);
		seconds1 = Math.floor(seconds1);
		seconds2 = Math.round((sec11 - seconds1)*10d);
		Image min = images.get(minutes);
		Image sec1 = images.get((int)seconds1);
		Image sec2 = images.get((int)seconds2);
		
			 //g.clear();
			 g.drawImage(min, 0, 0, false);
			 g.drawImage(colonn,32,0, false);
			 g.drawImage(sec1, 38, 0, false);
			 g.drawImage(sec2, 70, 0, false);
			 //g.drawString(""+seconds2, 1, 40);
			 
			//Sound.beep();
			 
			 if (counter==0)
				 Sound.buzz();
			Thread.sleep(1000);
		 
	}
		}		
		 //Thread.sleep(2000);
		 //LCD.bitBlt(data, 65, 84, 0, 0, 65, 84, 65, 84, 0);
//		 LCD.drawString("Hello World", 20, 20);
//		 try {
//			Thread.sleep(100000);
//		
//		 } catch (InterruptedException e) {
//			
//		}
//		 
//		 Sound.setVolume(100);
//		 
//		 
//		 
//		danger = new File("Danger.rso");
//		 
//		 
//		 
//		 Sound.playSample(danger);
//		 
		 // when bomb lays on the brick, sensor is on
		 TouchSensor theBomb = new TouchSensor(SensorPort.S1);
		 while (!theBomb.isPressed());
		 Sound.beep();
		Thread.sleep(1000);
	   
	 }
		
}