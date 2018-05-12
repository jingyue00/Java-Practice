package edu.nyu.cs9053.homework4;

public enum OlympicGame {
	
	FirstOlympicGame("France"),

	SecondOlympicGame("Switzerland"), 

	ThirdOlympicGame ("United States"), 

	FourthOlympicGame("Germany"), 

	FifthOlympicGame("Switzerland"), 

	SixthOlympicGame("Norway"), 

	SeventhOlympicGame("Italy"), 

	EighthOlympicGame("United States"), 

	NinethOlympicGame("Austria"), 

	TenthOlympicGame("France"), 

	EleventhOlympicGame("Japan"), 

	TwelfthOlympicGame("Austria"), 

	ThirteenthOlympicGame("United States"), 

	FourteenthOlympicGame("Yugoslavia"), 

	FifteenthOlympicGame("Canada"),

	SixteenthOlympicGame("France"), 

	SeventeenthOlympicGame("Norway"), 

	EighteenthOlympicGame("Japan"), 

	NineteenthOlympicGame("United States"), 

	TwentiethOlympicGame("Italy"), 

	Twenty_FirstOlympicGame("Canada"),

	Twenty_SecondOlympicGame("Russia"), 

	Twenty_ThirdOlympicGame("South Korea");

	private final String hostCountry;

	OlympicGame(String hostCountry) {
		this.hostCountry = hostCountry;
	}

	public String getHostCountry() {
		return hostCountry;
	}

	public static void printHostCountry(OlympicGame ... OlympicGames) {
		if (OlympicGames == null || OlympicGames.length == 0) {
			return;
		}
		for (OlympicGame hostCountry : OlympicGames) {
			System.out.printf("%s%n", hostCountry.getHostCountry());
		}
	}
}


	

