function Cup(id) {
	this.id = id;
	this.coffee = null;
	this.size = "NORMAL"; // or BIG
	this.condiments = [];
	
	// method addCondiment()
	this.addCondiment = function(condiment){
		this.condiments.push(condiment);
	};
	
	// method: getPrice()
	this.getPrice = function(){
		var price = 0.0;
		if(this.coffee != null){
			price = parseFloat(this.coffee.price);
		}
		if(this.size != undefined && this.size.toUpperCase() == "BIG"){
			price *= 1.2;
		}
		
		for(var i = 0; i<this.condiments.length; i++){
			price += parseFloat(this.condiments[i].price);
		}
		return price.toFixed(2);
	}
}