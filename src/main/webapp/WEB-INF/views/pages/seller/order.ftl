<#ftl encoding='UTF-8'>

<#include "../layout_base.ftl">
<#macro title> 
</#macro>

<#macro css_custom>
    <link href="/CoffeeShop/resources/css/page_order.css" rel="stylesheet">
</#macro>
	
<#macro script_custom>
	<script type="text/javascript" src="/CoffeeShop/resources/js/model/coffeeModel.js" ></script>
	<script type="text/javascript" src="/CoffeeShop/resources/js/model/condimentModel.js" ></script>
	<script type="text/javascript" src="/CoffeeShop/resources/js/seller/order.js" ></script>
</#macro>
<#macro page_header>
	<#include "../../static/header.ftl">
</#macro>

<#macro page_content>
	<div class="container">
	    <div class="row">
			<div class="col-sm-12 col-md-11 col-md-offset-1">
				<div>
					<button type="button" class="btn btn-primary" id="btn-new-order">
						 <span class="glyphicon glyphicon-plus"></span> <@spring.message "label.newOrder"/>
					</button>		
				</div>
				<table id="table_order" class="table table-hover">
					<thead>
						<tr>
							<th class="col-sm-3 col-lg-3 text-center"><@spring.message "label.cfName"/></th>
							<th class="col-sm-1 col-lg-2"><@spring.message "label.cfSize"/></th>
							<th class="col-sm-2 col-lg-2"><@spring.message "label.cfCondiment"/></th>
							<th class="col-sm-1 col-lg-1"></th>
							<th class="col-sm-1 col-lg-1"><@spring.message "label.cfPrice"/></th>
							<th class="col-sm-2 col-lg-2 text-right"></th>
						</tr>
					</thead>
					<tbody>					
	                    <tr id="add_new">
	    					<td colspan="6" align='center'>
	                            <button type="button" style="background-color:#fff;" onClick="addCupFunction()">
	    							<img src="resources/img/plus-icon.png" width="40" height="40" /> <@spring.message "label.addCup"/>
								</button>
							</td>
						</tr>
	
						<tr >
							<td colspan="4" align="right"><h3><strong><@spring.message "label.total"/><strong></h3></td>
							<td id="total_price" colspan="2" align="left"><h3>
									<strong>0</strong>
								</h3></td>
						</tr>
						<tr>
							<td colspan="4" align="right"><h4><@spring.message "label.customerPay"/></h4></td>
							<td id="customer_pay" colspan="2" align="left">
								<input type="number" class="form-control" min="0" step="0.01" onKeyUp="updateRefundFunction()">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="right"><h4><@spring.message "label.refund"/></h4></td>
							<td id="customer_refund" colspan="2" align="left"><h4><strong>0</strong></h4></td>
						</tr>
						<tr>
							<td colspan="4" align="right">
								<button id="btn-checkout" type="button" class="btn btn-success btn-lg" disabled="true">
									<@spring.message "label.checkout"/> <span class="glyphicon glyphicon-play"></span>
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- modal row as a cup -->
	<div id="row_to_insert" style="visibility: hidden;">
		<table>
			<tbody>
				<tr name="cup0">
					<td align="center">
						<div class="alert alert-success" style="width:100%;">
							<text class='name' ><strong><@spring.message "label.chooseOneBelow"/></strong></text>
							<text class='id' style="display:none;"></text>
							<text class='price' style="display:none;"></text>
						  	
						</div>
												
						<div class="dropdown">
						  	<button class="btn-default dropbtn"> <i class="fa fa-search"></i> <@spring.message "label.chooseCf"/></button>
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
							<input type='checkbox' name='coffee-size' value='big' onClick="onSelectSizeFunction($(this))"><@spring.message "label.bigSize"/>
					</td>
					<td >
						<#list model['listCondiment'] as condiment> 
							   <input type='checkbox' name='coffee-condiment' value=${condiment.price} onClick="onSelectCondimentFunction($(this))">
							   <b>
							   		<text type='hide' class='id'>${condiment.id}</text>.<text class='name'>${condiment.name}</text> (<text class='price'>${condiment.price}</text>)<br>
							   	</b>
							   	
						 </#list>
					</td>
					<td></td>
					<td>
						<text name="cup-price">0</text>
					</td>
					<td>
	                    <p>
	                      	<button name="btn_remove_cup" type="button" class="btn btn-danger" onClick="removeCupFunction($(this))">
								<span class="glyphicon glyphicon-remove"> </span> <@spring.message "label.remove"/>
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
					    <h4 class="modal-title"><strong><@spring.message "label.receipt"/></strong></h4>
				   </div>
				
				   <div class="modal-body">
				   		<table id="table-revieworder" class="table table-hover">
							<thead style="background-color: blue; color:white;">
								<tr>
									<th class="col-sm-3"><@spring.message "label.cfName"/></th>
									<th class="text-center col-sm-1"><@spring.message "label.cfSize"/></th>
									<th class=" col-sm-3"><@spring.message "label.cfCondiment"/></th>
									<th class="text-center col-sm-2"><@spring.message "label.cfPrice"/></th>
								</tr>
							</thead>
							<tbody>			
			                    <tr  id='revieworder-total'>
									<td colspan="3" align="right"><h3><strong><@spring.message "label.total"/><strong></h3></td>
									<td class="text-center">
										<h3>
											<strong class="price">0</strong>
										</h3>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				   <div class="modal-footer">
					    <button id="reject" type="button" class="btn btn-default modal-close" data-dismiss="modal"><@spring.message "label.global.close"/></button>
				   </div>
			  </div>
		 </div>
	</div> 

</#macro>

<#macro page_footer>
	<#include "../../static/footer.ftl">
</#macro>

<@display/>