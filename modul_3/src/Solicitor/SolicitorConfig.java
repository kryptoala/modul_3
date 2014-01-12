package Solicitor;

import java.util.Vector;

/**
 * Klasa opisujaca parametry graficznego interfejsu uzytkownika programu.
 *
 */
public class SolicitorConfig {

	/**
	 * Tablica lancuchow znakowych zawierajacych nazwy plikow graficznych wyswietlanych w menu glownym.
	 */
	public static String[] MenuButtons = { "odtworz_sekret.png", "odszyfruj_testament.png", "odczytaj_testament.png", "zamknij.png", "podzial_majatku.png"};
	
	/**
	 * Lancuch znakowy stanowiacy tytul menu.
	 */
	public static String MenuTitle = "Podzial majatku";

	/**
	 * Tablica zmiennych typu int stanowiacych wymiary okna menu glownego.
	 */
	public static int[] MenuSize = { 400, 285 };
	
	/**
	 * Zmienna boolowska opisujaca, czy uzytkownik korzysta z modulu odtwarzania sekretu (true), czy nie (false).
	 */
	static boolean regenerator = false;
	
	/**
	 * Tablica zmiennych typu int stanowiacych wymiary okna sluzacego do odtwarzania sekretu.
	 */
	public static int[] RegeneratorWindowSize = {510, 500};
	
	/**
	 * Tablica lancuchow znakowych zawierajacych nazwy plikow graficznych wyswietlanych w oknie odtwarzania sekretu.
	 */
	public static String[] RegeneratorButtons = { "odtwarzanie_sekretu.png", "odtwarzanie_sekretu_ostatni_spadkobierca.png", "dalej.png", "wroc_do_menu.png", "zamknij.png", "odtwarzanie_sekretu1.png"};
	
	/**
	 * Tablica lancuchow znakowych zawierajacych nazwy plikow graficznych wyswietlanych w okienku powiadomien pojawiajacym sie przy procedurze odtwarzania sekretu.
	 */
	public static String[] RegeneratorNotifications = {"odtwarzanie_sekretu_blad.png", "odtwarzanie_sekretu_ok.png", "niepoprawna_czesc_sekretu.png"};
	
	/**
	 * Wektor lancuchow znakowych bedacych lista spadkobiercow.
	 */
	public static Vector<String[]> SharersList = new Vector();
	
	
	/**
	 * Wektor zmiennych typu Integer bedacych kolejnymi numerami czesci sekretu.
	 */
	public static Vector<Integer> Shares = new Vector(); // po kolei numery jakie mialy czesci
	
	/**
	 * Lancuch znakow stanowiacy duza liczbe pierwsza. ?!
	 */
	public static String Prime;
	
	/**
	 * Zmienna binarna okreslajaca, czy aktualna czesc jest ostatnia (true), czy nie (false).
	 */
	static boolean last = false;

	/**
	 * Zmienna binarna okreslajaca, czy aktualna czesc jest pierwsza (true), czy nie (false).
	 */
	static boolean first = true;
	
	/**
	 * Zmienna typu int okreslajaca liczbe czesci niezbednych do odtworzenia sekretu.
	 */
	static int needed_parts = 100;
	
	/**
	 * Zmienna typu int stanowiaca licznik pokazujacy numer aktualnie przetwarzanej czesci sekretu.
	 */
	static int current_part = 1; // ile razy wpisywano
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy operacje na wielomianach Lagrange'a powiodly sie (true), czy nie (false). ?! 	
	 */
	static boolean lagranged = false; // czy rozwielomianowanie sie udalo
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy wpisywane dane maja dobra (false), czy zla (true) postac.
	 */
	static boolean wrong_form = false; // czy wpisywane rzeczy maja dobra postac
	
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy nalezy zresetowac wartosci wszystkich inne zmienne typu boolowskiego (true), czy nie (false).
	 */
	static boolean clear = false; // czy resetowac wszystkie booleany - sytuacja w ktorej ktos wpisal zla forme
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy modul odszyfrowywania jest uruchomiony (true), czy nie (false).
	 */
	static boolean decipherer = false;
	
	/**
	 * Tablica zmiennych typu int okreslajacych wymiary okna sluzacego do odszyfrowywania testamentu.
	 */
	public static int[] DeciphererWindowSize = {520, 500};
	
	/**
	 * Tablica lancuchow znakowych zawierajacy nazwy plikow graficznych wyswietlanych w oknie sluzacym do odszyfrowywania tresci testamentu.
	 */
	public static String[] DeciphererButtons = { "odszyfrowywanie_testamentu.png", "odszyfrowanie.png", "odszyfruj.png", "wroc_do_menu.png", "zamknij.png"};
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy tekst zostal odszyfrowany (true), czy nie (false).
	 */
	static boolean deciphered = false;
	
	/**
	 * Tablica lancuchow znakowych okreslajacych nazwy plikow graficznych wyswietlanych w oknach powiadomien wyswietlanych w procesie odszyfrowywania testamentu.
	 */
	public static String[] DeciphererNotifications = {"odszyfrowywanie_blad.png", "odszyfrowywanie_ok.png"};
	
	/**
	 * Lancuch znakowy zawierajacy nazwe pliku, w ktorym znajduje sie zaszyfrowany tekst testamentu.
	 */
	static String CipheredPath = "testament_zaszyfrowany.txt";
	
	/**
	 * Lancuch znakowy zawierajacy nazwe pliku, w ktorym znajduje sie jawny tekst testamentu.
	 */
	static String DecipheredPath = "testament.txt";
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy uruchomiony jest modul odczytywania testamentu (true), czy nie (false).
	 */
	static boolean reader = false;
	
	/**
	 * Tablica zmiennych typu int okreslajacych wymiary okna sluzacego do odczytywania tresci testamentu.
	 */
	public static int[] ReaderWindowSize = {520, 500};
	
	/**
	 * Tablica lancuchow znakowych zawierajacych nazwy plikow graficznych wyswietlanych w oknie sluzacym do odczytywania tresci testamentu.
	 */
	public static String[] ReaderButtons = { "odczytywanie_testamentu.png", "weryfikacja.png", "weryfikuj.png", "wroc_do_menu.png", "zamknij.png"};
	
	/**
	 * ?!
	 */
	static boolean read = false;
	
	/**
	 * Lancuch znakow stanowiacy nazwe pliku, w ktorym znajduje sie podpis cyfrowy.
	 */
	static String Sign = "podpis.txt";
	
	/**
	 * Lancuch znakow stanowiacy nazwe pliku, w ktorym znajduje sie klucz publiczny osoby spisujacej testament.
	 */
	static String Public_key = "klucz_publiczny.txt";
	
	/**
	 * Tablica lancuchow znakowych okreslajacych nazwy plikow graficznych wyswietlanych w oknach powiadomien wyswietlanych w procesie odczytywania testamentu.
	 */
	public static String[] ReaderNotifications = {"weryfikacja_blad.png", "weryfikacja_ok.png"};
	
	/**
	 * Tablica zmiennych typu int okreslajacych wymiary okna powiadomien.
	 */
	public static int[] NotificationSize = {500, 200};
	
	/**
	 * Zmienna typu boolowskiego okreslajaca, czy testament zostal poprawnie zweryfikowany i odczytany (true), czy nie (false).
	 */
	static boolean success = false;
	
}
