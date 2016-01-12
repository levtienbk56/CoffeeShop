<div class="container">
    <div class="row">
		<div class="col-sm-12 col-md-11 col-md-offset-1">
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
					<tr>
						<td >
							<#list model["listCoffee"] as coffee> 
								   <button id="btn_coffee" name='coffee-name'><text color='red'>${coffee.name}</text></button><br>
							 </#list>
						</td>
						<td>
							<div class="checkbox">
								<input type='checkbox' name='coffee-size' value='big'>Big Size?
							</div>
						</td>
						<td >
							<#list model['listCondiment'] as condiment> 
								   <input type='checkbox' name='coffee-condiment'><text>${condiment.name}</text><br>
							 </#list>
						</td>
						<td>
							<input type="number" class="form-control" id="coffee-quantity" min="1" value="1">
						</td>
						<td>0VND
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
						<td class="text-right"><h3>
								<strong>$31.53</strong>
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

