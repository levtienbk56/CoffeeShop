<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
</style>

<div class="container">
    <div class="row">
		<div class="col-sm-12 col-md-11 col-md-offset-1">
			<div>
				<button type="button" class="btn btn-primary">
					New Order <span class="glyphicon glyphicon-plus"></span>
				</button>			
			</div>
			<table id="table_order" class="table table-hover">
				<thead>
					<tr>
						<th class="col-sm-1 col-md-3">Coffee</th>
						<th class="col-sm-1 col-md-1">Size</th>
						<th class="col-sm-1 col-md-2">Condiment</th>
						<th class="col-sm-1 col-md-1">Quantity</th>
						<th class="col-sm-1 col-md-1">Price</th>
						<th class="col-sm-1 col-md-1"></th>
					</tr>
				</thead>
				<tbody>					
                    <tr id="add_new">
    					<td colspan="6" align='center'>
                            <button type="button" style="background-color:#fff;" onClick="addCupFunction()">
    							<img src="http://www2.psd100.com/ppp/2013/12/1301/Add-the-green-button-1214031005.png_s.jpg" width="40" height="40" /> Add a Cup
							</button>
						</td>
					</tr>

					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><h3>Total</h3></td>
						<td class="text-right" id='total_price'><h3>
								<strong>0$</strong>
							</h3></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-shopping-cart"></span> Continue
								Shopping
							</button>
						</td>
						<td>
							<button type="button" class="btn btn-success" onClick="checkoutFunction2()">
								Checkout <span class="glyphicon glyphicon-play"></span>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<div id="row_to_insert" style="visibility: hidden;">
	<table>
		<tbody>
			<tr name="cup0">
				<td align="center">
					<div class="alert alert-success" style="width:100%;">
						<text class='name' ><strong>Choose a Coffee</strong></text>
						<text class='id' style="display:none;"></text>
						<text class='price' style="display:none;"></text>
					  	
					</div>
											
					<div class="dropdown">
					  	<button class="dropbtn">Coffees</button>
					  	<div class="dropdown-content">
					  		<#list model["listCoffee"] as coffee>
					    		<a class="coffee-name" href="#"  onClick="updateCoffeeNameFunction($(this))">
					    			<text class='name'>${coffee.name}</text>
					    			<text class='id' style="display:none;">${coffee.id} </text>
					    			<text class='price' style="display:none;">${coffee.price} </text>
					    		</a>
					    	</#list>
					  	</div>
					</div>
				</td>
				<td>
						<input type='checkbox' name='coffee-size' value='big'>Big Size?
				</td>
				<td >
					<#list model['listCondiment'] as condiment> 
						   <input type='checkbox' name='coffee-condiment' value=${condiment.price}>
						   <b>
						   		<text type='hide' class='id'>${condiment.id}</text>.<text class='name'>${condiment.name}</text> (<text class='price'>${condiment.price}</text>$)<br>
						   	</b>
						   	
					 </#list>
				</td>
				<td>
					<input type="number" name='coffee-quantity'  class="form-control" min="1" value='1' style="width:50%;">
				</td>
				<td>
					<text name="cup-price">0</text>$
				</td>
				<td>
                    <p>
                      	<button name="btn_remove_cup" type="button" class="btn btn-danger" onClick="removeCupFunction($(this))">
							<span class="glyphicon glyphicon-remove"> </span> Remove
						</button>
                    </p>
				</td>
			</tr>
		</tbody>
	</table>
</div>



