/*
Core JavaScript functionality for the application.  Performs the required
Restful calls, validates return values, and populates the member table.
 */

/* Get the member template */
function getMemberTemplate() {
	$.ajax({
        url: "resources/tmpl/member.tmpl",
        dataType: "html",
        success: function( data ) {
            $( "head" ).append( data );
            updateMemberTable();
        }
    });
}

/* Builds the updated table for the member list */
function buildMemberRows(members) {
	return _.template( $( "#member-tmpl" ).html(), {"members": members});
}

/* Uses JAX-RS GET to retrieve current member list */
function updateMemberTable() {
   $.ajax({
	   url: "rest/members/json",
	   cache: false,
	   success: function(data) {
            $('#members').empty().append(buildMemberRows(data));
       },
       error: function(error) {
            //console.log("error updating table -" + error.status);
       }
   });
}

/* Uses JAX-RS GET to retrieve Accounts */
function updateAccountsTable() {
   $.ajax({
	   url: "rest/costs/accounts",
	   cache: false,
	   dataType: "xml",
	   success: function(xmldata) {
		   buildOptionsForAccount_id(xmldata);
       },
       error: function(error) {
            console.log("error updating table -" + error.status);
       }
   });
}

/* Builds the updated table for the member list*/ 
function buildOptionsForAccount_id(xmldata) {
	$(xmldata).find("account").each(function() {
        var id = $(this).find("id").text();
        var label = $(this).find("label").text();
        $('#accounts').append( '<option value="'+id+'">'+label+'</option>');
    });
	/*
	_.each(accounts, function(account) {
		 $('#accounts').append( '<option value="'+account.id+'">'+account.label+'</option>');
	});
	*/
}

function subaccount(){
	var account_id = $("#accounts").val();
	if(account_id=="default"){
		$('#subaccounts')
        .find('option')
        .remove()
        .end();	
	}else{
		$.ajax({
		url: "rest/costs/accounts/subaccounts/"+account_id,
		dataType: "xml",
		cache: false,
		success: function(xmldata) {
			$('#subaccounts')
	        .find('option')
	        .remove()
	        .end();
			$(xmldata).find("subAccount").each(function() {
				var id = $(this).find("id").text();
			    var label = $(this).find("label").text();
			    //alert("id: "+id+" Label: "+label );
			    $('#subaccounts')
			    .find('option')
			    .end()
			    .append('<option value="'+id+'">'+label+'</option>');
			});
			   
	     },
	     error: function(error) {
	    	 console.log("error updating table -" + error.status);
	     }
	     });
	}
}	


/*
Attempts to register a new member using a JAX-RS POST.  The callbacks
the refresh the member table, or process JAX-RS response codes to update
the validation errors.
 */
function registerMember(formValues) {
   //clear existing  msgs
   $('span.invalid').remove();
   $('span.success').remove();

   $.post('rest/members', formValues,
         function(data) {
            //console.log("Member registered");

            //clear input fields
            $('#reg')[0].reset();

            //mark success on the registration form
            $('#formMsgs').append($('<span class="success">Member Registered</span>'));

            updateMemberTable();
         }).error(function(error) {
            if ((error.status == 409) || (error.status == 400)) {
               //console.log("Validation error registering user!");

               var errorMsg = JSON.parse(error.responseText);

               $.each(errorMsg, function(index, val){
                  $('<span class="invalid">' + val + '</span>')
                        .insertAfter($('#' + index));
               });
            } else {
               //console.log("error - unknown server issue");
               $('#formMsgs').append($('<span class="invalid">Unknown server error</span>'));
            }
         });
}

function registerExpenditures(formValues) {
	   //clear existing  msgs
	   $('span.invalid').remove();
	   $('span.success').remove();

	   $.post('rest/costs', formValues,
	         function(data) {
	            console.log("Expenditures registered");

	            //clear input fields
	            $('#reg')[0].reset();

	            //mark success on the registration form
	            $('#formMsgs').append($('<span class="success">Kosten wurden verbucht</span>'));

	            
	         }).error(function(error) {
	            if ((error.status == 409) || (error.status == 400)) {
	               console.log("Validation error registering user!");

	               var errorMsg = JSON.parse(error.responseText);

	               $.each(errorMsg, function(index, val){
	                  $('<span class="invalid">' + val + '</span>')
	                        .insertAfter($('#' + index));
	               });
	            } else {
	               console.log("error - unknown server issue");
	               $('#formMsgs').append($('<span class="invalid">Unknown server error</span>'));
	            }
	         });
	}