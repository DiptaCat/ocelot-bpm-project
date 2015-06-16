package ocelot

class Palette {

	static hasMany = [paletteItems: PaletteItem, customPaletteItems: CustomPaletteItem]
	static belongsTo = [user: Member]

	static constraints = {
	}
}
