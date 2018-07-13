<#import "/spring.ftl" as spring/>
<#assign url = "/inexture-sample/">
<@layout>
<div id="reg" class="section ">
		<!-- Container -->
		<div class="container">
			<!-- Row -->
			<div class="row">
					<form id="myform" class="form-horizontal" enctype="multipart/form-data"
							 method="POST" data-toggle="validator">
							<div class="form-group has-feedback">
							
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> First
									Name</label>
								<div class="col-sm-10">
								<@spring.formInput "user.firstname","class='form-control' id='inputName'
										placeholder='Enter your first name example john'
									    data-error='first name should not be blank'  required"/>
									    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
								
							</div>
							
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> Last
									Name</label>
								<div class="col-sm-10">
								<@spring.formInput "user.lastname","class='form-control' id='inputName'
										placeholder='Enter your first name example john'
									    data-error='first name should not be blank'  required"/>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback" id="emaildiv">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Email</label>
								<div class="col-sm-10">
								<@spring.formInput "user.email","class='form-control' id='inputName'
										placeholder='Enter your first name example john'
									  data-error='please enter valid email id format' onfocusout='checkUser()' required","email"/>
								</div>
								
							</div>
							<div class="form-group has-feedback" id="pwddiv" >
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Password</label>
								<div class="col-sm-10">
								<@spring.formPasswordInput "user.password", "class='form-control' id='inputPassword'
										placeholder='1@Mypass' name='password'
									    data-minlength='6' maxlength='15'  required"/>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group has-feedback" id="confpassdiv">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Confirm Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control"
										 placeholder="1@Mypass" 
										 data-minlength="6" data-match="#inputPassword"
										 data-match-error="Whoops, these don't match" placeholder="Confirm password" required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Moblie</label>
								<div class="col-sm-10">
									<@spring.formInput "user.mobile", "class='only-number form-control' id='inputmobile' min='1' max='10' 
										placeholder='8844662211' data-minlength='10' pattern='^[0-9]+$' maxlength='10' required"/>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>
							
							<div class="form-group has-feedback">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Date
									of birth</label>
								<div class="col-sm-10">
								<div class="input-group date" >
								<@spring.formInput "user.dob", "class='datepicker form-control' id='nputdob'  placeholder='21/2/1997' required","date"/>
   									 <div class="input-group-addon">
        								<span class="glyphicon glyphicon-th"></span>
    								</div>
										
								</div>
								<div class="help-block with-errors"></div>
								<!--  	<input type="date" class="form-control" id="inputdob"
										placeholder="21/2/1997" name="dob" value="${date}" required>-->
								</div>
							</div>

							<div class="form-group  has-feedback">
								<label class="col-sm-2 control-label">image</label>
								<div class="col-sm-10">
									<ul id="media-list" class="clearfix">
										<li class="myupload"><span><i class="fa fa-plus"
												aria-hidden="true"></i>
												<@spring.formInput "user.userImages", "click-type='type2'
												id='picupload' class='picupload' onchange='checkImage()' multiple", "file"/>
												</span></li>
									</ul>		
									<div id="fluploadmsg" class="help-block with-errors"></div>						
								</div>
							</div>




							<div class="form-group has-feedback" >
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> Technology</label>
								<div class="col-sm-10">

	
									<@spring.formSingleSelect "user.tech",techmap, "class='form-control' data-error='please select any one' required"/>
																	
																						
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group has-feedback">
							<div class="radio">
								<div class="fix-label">
									<label  class="col-sm-2 control-label"><span class="text-danger">*</span> gender</label>
										<div class="col-sm-10">
										<@spring.formRadioButtons "user.gender", langtech, ""/>
											
										</div>
								</div>
							</div>
							</div>
							<div class="form-group has-feedback">
							<div>
									<div class="checkbox">
										<div class="fix-label">
										
											<label for="inputPassword3" class="col-sm-2 control-label"><span class="text-danger">*</span> Language</label>
											
											<div class=" col-sm-10">
						
		
													<label class="checkbox-inline"> 
													
													<@spring.formCheckbox "user.languages", "data-validate='false' onchange='validateCheck()'  data-error='please check on languages you know' "/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
									</div>
								</div>
								</div>
							<div class="padding-30"></div>
							<div class="panel panel-default">
								<!-- Default panel contents -->
								<div class="panel-heading">Enter your Address details</div>
								<ul class="list-group" id="list">
									<li class="list-group-item">
										<div class="well clearfix">
											<div id="czContainer">
												<div id="first">
													<div class="recordset">
														<div class="fieldRow clearfix">
															<div class="panel-body" id="pbody">
															<#-- <c:if test="${requestScope.addrslist ne null}">
															<input type="hidden" name="idaddress" id="id_idaddress_1_" value="x">
															</c:if> -->
																
																<div class="form-group  has-feedback">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 1</label>
																	<div class="col-sm-10">
																	<@spring.formTextarea "AddressList[1].addressline1", " rows='3' class='dynmc-input form-control' id='id_address1_1_' maxlength='100'  required='true' "/>
																			<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			<div class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="form-group has-feedback">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 2</label>
																	<div class="col-sm-10">
																	<@spring.formTextarea "AddressList[1].addresslines", " rows='3' class='dynmc-input form-control' id='id_addresss2_1_' maxlength='100'  "/>
																			<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			<div class="help-block with-errors"></div>
																	</div>
																</div>

																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">pin</label>
																	<div class="col-sm-10">
																	<@spring.formInput "user.AddressList[1].pin", "class='dynmc-input only-number form-control' placeholder='382481'  id='id_pin_1_'  data-minlength='6'  maxlength='6' data-error='first name should not be blank'  required='true'"/>
																		<input type="text" class="dynmc-input only-number form-control"
																			placeholder="382481" name="AddressList[1].pin" id="id_pin_1_"  data-minlength="6"  maxlength="6" data-error="first name should not be blank"  required="true"/>
																			<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			<div class="help-block with-errors"></div>
																	</div>
																</div>


																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">city</label>
																	<div class="col-sm-10">
																	<@spring.formSingleSelect "user.AddressList[1].city", city.map, "class='dynmc-input form-control'"/>
																	
																		<div class="help-block with-errors"></div>
																	</div>
																</div>


																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">state</label>
																	<div class="col-sm-10">
																	<@spring.formSingleSelect "user.AddressList[1].state", state.map, "class='dynmc-input form-control'"/>
																																			<div class="help-block with-errors"></div>
																	</div>
																</div>

																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">country</label>
																	<div class="col-sm-10" >
																	
																	<@spring.formSingleSelect "user.AddressList[1].country", country.map, "class='dynmc-input form-control'"/>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
											</div>
										</div>




									</li>
								</ul>

							</div>
							<div class="form-group">
								<div>
				
									<button type="submit" id="mybutton"     class="btn btn-primary">Sign Up</button>
						
								</div>
							</div>
						</form>
					</div>

			
					<img id="ItemPreview" src="" />
				</div>
			</div>
		</div>
	</div>
		
<!-- 	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script> -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js" type="text/javascript"></script>
	<script src="${url}/js/jquery.czMore-latest.js"></script>
	<script src="${url}/js/jquery.czMore-1.5.3.2.js"></script>
	<script type="text/javascript">
		//One-to-many relationship plugin by Yasir O. Atabani. Copyrights Reserved.
		$("#czContainer").czMore();
		
	</script>
	
	
	<script src="${url}/js/validate-email.js"></script>
	<script src="${url}/js/myvalidation.js"></script>
	<script src="${url}/js/fileupload.js"></script>
</@>layout>	
    