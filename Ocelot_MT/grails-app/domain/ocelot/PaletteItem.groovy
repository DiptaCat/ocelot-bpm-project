package ocelot

import palette.APaletteItem

class PaletteItem extends APaletteItem {

	boolean activated

	static belongsTo = [category: CategoryItem]

	static constraints = {
		props nullable: true
	}

	static mapping = {
		props type: "text"
		svg type: "text"
	}
}
