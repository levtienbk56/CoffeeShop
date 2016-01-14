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
					<tr id="cup1">
						<td >
							<select name='coffee-name'>
								<#list model["listCoffee"] as coffee>
									<option value=${coffee.price}>
										<text class='id'>${coffee.id}</text>.<text class='name'>${coffee.name}</text> (<text class='price'>${coffee.price}</text>$)
									</option>
								</#list>
							</select>
						</td>
						<td>
								<input type='checkbox' name='coffee-size' value='big'>Big Size?
						</td>
						<td >
							<#list model['listCondiment'] as condiment> 
								   <input type='checkbox' name='coffee-condiment' value=${condiment.price}>
								   		<text type='hide' class='id'>${condiment.id}</text>.<text class='name'>${condiment.name}</text> (<text class='price'>${condiment.price}</text>$)<br>
							 </#list>
						</td>
						<td>
							<input type="number" name='coffee-quantity'  class="form-control" min="1" value='1'>
						</td>
						<td>
							<text name="cup-price">0</text>$
						</td>
						<td>
                            <p>
                              	<button id="btn_remove_cup" type="button" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"> </span> Remove
								</button>
                            </p>
						</td>
					</tr>
					
                    <tr id="add_new">
    					<td colspan="6" align='center'>
                            <button type="button" style="background-color:#fff;" onClick="addCupFunction2()">
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
							<button type="button" class="btn btn-success">
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
			<tr id="cup0">
						<td >
							<select name='coffee-name'>
								<#list model["listCoffee"] as coffee>
									<option value=${coffee.price}>
										<text class='id'>${coffee.id}</text>.<text class='name'>${coffee.name}</text> (<text class='price'>${coffee.price}</text>$)
									</option>
								</#list>
							</select>
						</td>
						<td>
								<input type='checkbox' name='coffee-size' value='big'>Big Size?
						</td>
						<td >
							<#list model['listCondiment'] as condiment> 
								   <input type='checkbox' name='coffee-condiment' value=${condiment.price}>
								   		<text type='hide' class='id'>${condiment.id}</text>.<text class='name'>${condiment.name}</text> (<text class='price'>${condiment.price}</text>$)<br>
							 </#list>
						</td>
						<td>
							<input type="number" name='coffee-quantity'  class="form-control" min="1" value='1'>
						</td>
						<td>
							<text name="cup-price">0</text>$
						</td>
						<td>
                            <p>
                              	<button id="btn_remove_cup" type="button" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"> </span> Remove
								</button>
                            </p>
						</td>
					</tr>
		</tbody>
	</table>
</div>



