@file:Suppress("unused")

package kash

import kotlin.jvm.JvmSynthetic

enum class Currency(val symbol: String, val details: String,val lowestDenomination: UShort) {

	/**UAE dirham*/
	AED("د.إ;","UAE dirham",100u),

	/**Afghan afghani*/
	AFN("Afs","Afghan afghani",100u),

	/**Albanian lek*/
	ALL("L","Albanian lek",100u),

	/**Armenian dram*/
	AMD("AMD","Armenian dram",100u),

	/**Netherlands Antillean gulden*/
	ANG("NAƒ","Netherlands Antillean gulden",100u),

	/**Angolan kwanza*/
	AOA("Kz","Angolan kwanza",100u),

	/**Argentine peso*/
	ARS("$","Argentine peso",100u),

	/**Australian dollar*/
	AUD("$","Australian dollar",100u),

	/**Aruban florin*/
	AWG("ƒ","Aruban florin",100u),

	/**Azerbaijani manat*/
	AZN("AZN","Azerbaijani manat",100u),

	/**Bosnia and Herzegovina konvertibilna marka*/
	BAM("KM","Bosnia and Herzegovina konvertibilna marka",100u),

	/**Barbadian dollar*/
	BBD("Bds$","Barbadian dollar",100u),

	/**Bangladeshi taka*/
	BDT("৳","Bangladeshi taka",100u),

	/**Bulgarian lev*/
	BGN("BGN","Bulgarian lev",100u),

	/**Bahraini dinar*/
	BHD(".د.ب","Bahraini dinar",100u),

	/**Burundi franc*/
	BIF("FBu","Burundi franc",100u),

	/**Bermudian dollar*/
	BMD("BD$","Bermudian dollar",100u),

	/**Brunei dollar*/
	BND("B$","Brunei dollar",100u),

	/**Bolivian boliviano*/
	BOB("Bs.","Bolivian boliviano",100u),

	/**Brazilian real*/
	BRL("R$","Brazilian real",100u),

	/**Bahamian dollar*/
	BSD("B$","Bahamian dollar",100u),

	/**Bhutanese ngultrum*/
	BTN("Nu.","Bhutanese ngultrum",100u),

	/**Botswana pula*/
	BWP("P","Botswana pula",100u),

	/**Belarusian ruble*/
	BYR("Br","Belarusian ruble",100u),

	/**Belize dollar*/
	BZD("BZ$","Belize dollar",100u),

	/**Canadian dollar*/
	CAD("$","Canadian dollar",100u),

	/**Congolese franc*/
	CDF("F","Congolese franc",100u),

	/**Swiss franc*/
	CHF("Fr.","Swiss franc",100u),

	/**Chilean peso*/
	CLP("$","Chilean peso",100u),

	/**Chinese/Yuan renminbi*/
	CNY("¥","Chinese/Yuan renminbi",100u),

	/**Colombian peso*/
	COP("Col$","Colombian peso",100u),

	/**Costa Rican colon*/
	CRC("₡","Costa Rican colon",100u),

	/**Cuban peso*/
	CUC("$","Cuban peso",100u),

	/**Cape Verdean escudo*/
	CVE("Esc","Cape Verdean escudo",100u),

	/**Czech koruna*/
	CZK("Kč","Czech koruna",100u),

	/**Djiboutian franc*/
	DJF("Fdj","Djiboutian franc",100u),

	/**Danish krone*/
	DKK("Kr","Danish krone",100u),

	/**Dominican peso*/
	DOP("RD$","Dominican peso",100u),

	/**Algerian dinar*/
	DZD("د.ج","Algerian dinar",100u),

	/**Estonian kroon*/
	EEK("KR","Estonian kroon",100u),

	/**Egyptian pound*/
	EGP("£","Egyptian pound",100u),

	/**Eritrean nakfa*/
	ERN("Nfa","Eritrean nakfa",100u),

	/**Ethiopian birr*/
	ETB("Br","Ethiopian birr",100u),

	/**European Euro*/
	EUR("€","European Euro",100u),

	/**Fijian dollar*/
	FJD("FJ$","Fijian dollar",100u),

	/**Falkland Islands pound*/
	FKP("£","Falkland Islands pound",100u),

	/**British pound*/
	GBP("£","British pound",100u),

	/**Georgian lari*/
	GEL("GEL","Georgian lari",100u),

	/**Ghanaian cedi*/
	GHS("GH₵","Ghanaian cedi",100u),

	/**Gibraltar pound*/
	GIP("£","Gibraltar pound",100u),

	/**Gambian dalasi*/
	GMD("D","Gambian dalasi",100u),

	/**Guinean franc*/
	GNF("FG","Guinean franc",100u),

	/**Central African CFA franc*/
	GQE("CFA","Central African CFA franc",100u),

	/**Guatemalan quetzal*/
	GTQ("Q","Guatemalan quetzal",100u),

	/**Guyanese dollar*/
	GYD("GY$","Guyanese dollar",100u),

	/**Hong Kong dollar*/
	HKD("HK$","Hong Kong dollar",100u),

	/**Honduran lempira*/
	HNL("L","Honduran lempira",100u),

	/**Croatian kuna*/
	HRK("kn","Croatian kuna",100u),

	/**Haitian gourde*/
	HTG("G","Haitian gourde",100u),

	/**Hungarian forint*/
	HUF("Ft","Hungarian forint",100u),

	/**Indonesian rupiah*/
	IDR("Rp","Indonesian rupiah",100u),

	/**Israeli new sheqel*/
	ILS("₪","Israeli new sheqel",100u),

	/**Indian rupee*/
	INR("₹","Indian rupee",100u),

	/**Iraqi dinar*/
	IQD("د.ع","Iraqi dinar",100u),

	/**Iranian rial*/
	IRR("IRR","Iranian rial",100u),

	/**Icelandic króna*/
	ISK("kr","Icelandic króna",100u),

	/**Jamaican dollar*/
	JMD("J$","Jamaican dollar",100u),

	/**Jordanian dinar*/
	JOD("JOD","Jordanian dinar",100u),

	/**Japanese yen*/
	JPY("¥","Japanese yen",100u),

	/**Kenyan shilling*/
	KES("KSh","Kenyan shilling",100u),

	/**Kyrgyzstani som*/
	KGS("сом","Kyrgyzstani som",100u),

	/**Cambodian riel*/
	KHR("៛","Cambodian riel",100u),

	/**Comorian franc*/
	KMF("KMF","Comorian franc",100u),

	/**North Korean won*/
	KPW("W","North Korean won",100u),

	/**South Korean won*/
	KRW("W","South Korean won",100u),

	/**Kuwaiti dinar*/
	KWD("KWD","Kuwaiti dinar",100u),

	/**Cayman Islands dollar*/
	KYD("KY$","Cayman Islands dollar",100u),

	/**Kazakhstani tenge*/
	KZT("T","Kazakhstani tenge",100u),

	/**Lao kip*/
	LAK("KN","Lao kip",100u),

	/**Lebanese lira*/
	LBP("£","Lebanese lira",100u),

	/**Sri Lankan rupee*/
	LKR("Rs","Sri Lankan rupee",100u),

	/**Liberian dollar*/
	LRD("L$","Liberian dollar",100u),

	/**Lesotho loti*/
	LSL("M","Lesotho loti",100u),

	/**Lithuanian litas*/
	LTL("Lt","Lithuanian litas",100u),

	/**Latvian lats*/
	LVL("Ls","Latvian lats",100u),

	/**Libyan dinar*/
	LYD("LD","Libyan dinar",100u),

	/**Moroccan dirham*/
	MAD("MAD","Moroccan dirham",100u),

	/**Moldovan leu*/
	MDL("MDL","Moldovan leu",100u),

	/**Malagasy ariary*/
	MGA("FMG","Malagasy ariary",100u),

	/**Macedonian denar*/
	MKD("MKD","Macedonian denar",100u),

	/**Myanma kyat*/
	MMK("K","Myanma kyat",100u),

	/**Mongolian tugrik*/
	MNT("₮","Mongolian tugrik",100u),

	/**Macanese pataca*/
	MOP("P","Macanese pataca",100u),

	/**Mauritanian ouguiya*/
	MRO("UM","Mauritanian ouguiya",100u),

	/**Mauritian rupee*/
	MUR("Rs","Mauritian rupee",100u),

	/**Maldivian rufiyaa*/
	MVR("Rf","Maldivian rufiyaa",100u),

	/**Malawian kwacha*/
	MWK("MK","Malawian kwacha",100u),

	/**Mexican peso*/
	MXN("$","Mexican peso",100u),

	/**Malaysian ringgit*/
	MYR("RM","Malaysian ringgit",100u),

	/**Mozambican metical*/
	MZM("MTn","Mozambican metical",100u),

	/**Namibian dollar*/
	NAD("N$","Namibian dollar",100u),

	/**Nigerian naira*/
	NGN("₦","Nigerian naira",100u),

	/**Nicaraguan córdoba*/
	NIO("C$","Nicaraguan córdoba",100u),

	/**Norwegian krone*/
	NOK("kr","Norwegian krone",100u),

	/**Nepalese rupee*/
	NPR("NRs","Nepalese rupee",100u),

	/**New Zealand dollar*/
	NZD("NZ$","New Zealand dollar",100u),

	/**Omani rial*/
	OMR("OMR","Omani rial",100u),

	/**Panamanian balboa*/
	PAB("B./","Panamanian balboa",100u),

	/**Peruvian nuevo sol*/
	PEN("S/.","Peruvian nuevo sol",100u),

	/**Papua New Guinean kina*/
	PGK("K","Papua New Guinean kina",100u),

	/**Philippine peso*/
	PHP("₱","Philippine peso",100u),

	/**Pakistani rupee*/
	PKR("Rs.","Pakistani rupee",100u),

	/**Polish zloty*/
	PLN("zł","Polish zloty",100u),

	/**Paraguayan guarani*/
	PYG("₲","Paraguayan guarani",100u),

	/**Qatari riyal*/
	QAR("QR","Qatari riyal",100u),

	/**Romanian leu*/
	RON("L","Romanian leu",100u),

	/**Serbian dinar*/
	RSD("din.","Serbian dinar",100u),

	/**Russian ruble*/
	RUB("R","Russian ruble",100u),

	/**Saudi riyal*/
	SAR("SR","Saudi riyal",100u),

	/**Solomon Islands dollar*/
	SBD("SI$","Solomon Islands dollar",100u),

	/**Seychellois rupee*/
	SCR("SR","Seychellois rupee",100u),

	/**Sudanese pound*/
	SDG("SDG","Sudanese pound",100u),

	/**Swedish krona*/
	SEK("kr","Swedish krona",100u),

	/**Singapore dollar*/
	SGD("S$","Singapore dollar",100u),

	/**Saint Helena pound*/
	SHP("£","Saint Helena pound",100u),

	/**Sierra Leonean leone*/
	SLL("Le","Sierra Leonean leone",100u),

	/**Somali shilling*/
	SOS("Sh.","Somali shilling",100u),

	/**Surinamese dollar*/
	SRD("$","Surinamese dollar",100u),

	/**Syrian pound*/
	SYP("LS","Syrian pound",100u),

	/**Swazi lilangeni*/
	SZL("E","Swazi lilangeni",100u),

	/**Thai baht*/
	THB("฿","Thai baht",100u),

	/**Tajikistani somoni*/
	TJS("TJS","Tajikistani somoni",100u),

	/**Turkmen manat*/
	TMT("m","Turkmen manat",100u),

	/**Tunisian dinar*/
	TND("DT","Tunisian dinar",100u),

	/**Turkish new lira*/
	TRY("TRY","Turkish new lira",100u),

	/**Trinidad and Tobago dollar*/
	TTD("TT$","Trinidad and Tobago dollar",100u),

	/**New Taiwan dollar*/
	TWD("NT$","New Taiwan dollar",100u),

	/**Tanzanian shilling*/
	TZS("TZS","Tanzanian shilling",100u),

	/**Ukrainian hryvnia*/
	UAH("UAH","Ukrainian hryvnia",100u),

	/**Ugandan shilling*/
	UGX("USh","Ugandan shilling",100u),

	/**United States dollar*/
	USD("US$","United States dollar",100u),

	/**Uruguayan peso*/
	UYU("${"$"}U","Uruguayan peso",100u),

	/**Uzbekistani som*/
	UZS("UZS","Uzbekistani som",100u),

	/**Venezuelan bolivar*/
	VEB("Bs","Venezuelan bolivar",100u),

	/**Vietnamese dong*/
	VND("₫","Vietnamese dong",100u),

	/**Vanuatu vatu*/
	VUV("VT","Vanuatu vatu",100u),

	/**Samoan tala*/
	WST("WS$","Samoan tala",100u),

	/**Central African CFA franc*/
	XAF("CFA","Central African CFA franc",100u),

	/**East Caribbean dollar*/
	XCD("EC$","East Caribbean dollar",100u),

	/**Special Drawing Rights*/
	XDR("SDR","Special Drawing Rights",100u),

	/**West African CFA franc*/
	XOF("CFA","West African CFA franc",100u),

	/**CFP franc*/
	XPF("F","CFP franc",100u),

	/**Yemeni rial*/
	YER("YER","Yemeni rial",100u),

	/**South African rand*/
	ZAR("R","South African rand",100u),

	/**Zambian kwacha*/
	ZMK("ZK","Zambian kwacha",100u),

	/**Zimbabwean dollar*/
	ZWR("Z$","Zimbabwean dollar",100u);

    fun of(amount: UInt) = Money((amount * lowestDenomination).toULong(), this)
    fun of(amount: ULong) = Money((amount * lowestDenomination).toULong(), this)
    fun of(amount: Double) = Money((amount * lowestDenomination.toShort()).toULong(), this)
    @JvmSynthetic
    fun of(amount: Int) = Money((amount * lowestDenomination.toShort()).toULong(), this)
    @JvmSynthetic
    fun of(amount: Long) = Money((amount * lowestDenomination.toShort()).toULong(), this)
}