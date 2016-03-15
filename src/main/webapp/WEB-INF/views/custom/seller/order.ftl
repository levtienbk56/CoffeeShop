<#ftl encoding='UTF-8'> 

<style>
	.dropbtn {
	    color: black;
	    padding: 16px;
	    font-size: 16px;
	    cursor: pointer;
	}
	
	.dropdown {
	    position: inherit;
	    display: inline-block;
	}
	
	.dropdown-content {
	    display: none;
	    position: absolute;
	    background-color: #A0522D;
	    min-width: 160px;
	    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	}
	
	.dropdown-content a {
	    color: white;
	    padding: 12px 16px;
	    text-decoration: none;
	    display: block;
	}
	
	.dropdown-content a:hover {
		background-color: #773d22;
	}
	
	.dropdown:hover .dropdown-content {
	    display: block;
	}
	
	table#table_order{
		background-color: #ffffff;
	}
</style>

<div class="container">
    <div class="row">
		<div class="col-sm-12 col-md-11 col-md-offset-1">
			<div>
				<button type="button" class="btn btn-primary" id="btn-new-order">
					 <span class="glyphicon glyphicon-plus"></span> New Order
				</button>			
			</div>
			<table id="table_order" class="table table-hover">
				<thead>
					<tr>
						<th class="col-sm-3 col-lg-3 text-center">Coffee Name</th>
						<th class="col-sm-1 col-lg-2">Size</th>
						<th class="col-sm-2 col-lg-2">Condiment</th>
						<th class="col-sm-2 col-lg-1 text-center">Quantity</th>
						<th class="col-sm-1 col-lg-1"></th>
						<th class="col-sm-1 col-lg-1">Price ($)</th>
						<th class="col-sm-2 col-lg-2 text-right"></th>
					</tr>
				</thead>
				<tbody>					
                    <tr id="add_new">
    					<td colspan="7" align='center'>
                            <button type="button" style="background-color:#fff;" onClick="addCupFunction()">
    							<img src="http://www2.psd100.com/ppp/2013/12/1301/Add-the-green-button-1214031005.png_s.jpg" width="40" height="40" /> Add a Cup
							</button>
						</td>
					</tr>

					<tr >
						<td colspan="5" align="right"><h3><strong>Total $<strong></h3></td>
						<td id="total_price" colspan="2" align="left"><h3>
								<strong>0</strong>
							</h3></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h4>Customer Pay $</h4></td>
						<td id="customer_pay" colspan="2" align="left">
							<input type="number" class="form-control" min="0" step="0.01" onKeyUp="updateRefundFunction()">
						</td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h4>Refund $</h4></td>
						<td id="customer_refund" colspan="2" align="left"><h4><strong>0</strong></h4></td>
					</tr>
					<tr>
						<td colspan="7" align="right">
							<button id="btn-checkout" type="button" class="btn btn-success btn-lg" disabled="true">
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
						<text class='name' ><strong>(choose one below)</strong></text>
						<text class='id' style="display:none;"></text>
						<text class='price' style="display:none;"></text>
					  	
					</div>
											
					<div class="dropdown">
					  	<button class="btn-default dropbtn"> <i class="fa fa-search"></i> Choose a Coffee</button>
					  	<div class="dropdown-content">
					  		<#list model["listCoffee"] as coffee>
					    		<a class="coffee-name" onClick="onSelectCoffeeNameFunction($(this))">
					    			<text class='name'>${coffee.name}</text>
					    			<text class='id' style="display:none;">${coffee.id} </text>
					    			<text class='price' style="display:none;">${coffee.price} </text>
					    		</a>
					    	</#list>
					  	</div>
					</div>
				</td>
				<td>
						<input type='checkbox' name='coffee-size' value='big' onClick="onSelectSizeFunction($(this))">Big Size?
				</td>
				<td >
					<#list model['listCondiment'] as condiment> 
						   <input type='checkbox' name='coffee-condiment' value=${condiment.price} onClick="onSelectCondimentFunction($(this))">
						   <b>
						   		<text type='hide' class='id'>${condiment.id}</text>.<text class='name'>${condiment.name}</text> (<text class='price'>${condiment.price}</text>)<br>
						   	</b>
						   	
					 </#list>
				</td>
				<td>
					<input type="number" name="coffee-quantity"  class="form-control" min="1" value="1" onClick="onSelectQuantityFunction($(this))">
				</td>
				<td></td>
				<td>
					<text name="cup-price">0</text>
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

<!-- modal review order -->
<div class="modal fade" id="revieworder-modal" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
			   <div class="modal-header">
				    <button type="button" class="close modal-close" data-dismiss="modal">&times;</button>
				    <h4 class="modal-title"><strong>Receipt</strong></h4>
			   </div>
			
			   <div class="modal-body">
			   		<table id="table-revieworder" class="table table-hover">
						<thead>
							<tr>
								<th class="col-sm-3">Coffee</th>
								<th class="text-center col-sm-1">Size</th>
								<th class=" col-sm-3">Condiment</th>
								<th class="text-center col-sm-1">Quantity</th>
								<th class="text-center col-sm-2">Price ($)</th>
							</tr>
						</thead>
						<tbody>			
		                    <tr  id='revieworder-total'>
								<td colspan="4" align="right"><h4><strong>Total ($)<strong></h4></td>
								<td class="text-left">
									<h4>
										<strong class="price">0</strong>
									</h4>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			   <div class="modal-footer">
				    <button id="reject" type="button" class="btn btn-default modal-close" data-dismiss="modal">Close</button>
			   </div>
		  </div>
	 </div>
</div> 

<!-- JavaScript -->
<script type="text/javascript" src="resources/js/seller/order.js" ></script>