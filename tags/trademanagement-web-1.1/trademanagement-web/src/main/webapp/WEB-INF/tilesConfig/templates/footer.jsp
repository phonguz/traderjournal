<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="footer">
  <div class="container">
    <div class="row">
      <div class="col-md-5">
        <div class="main_trade"><a href="<%=request.getContextPath() %>/trade" class="navbar-brand-footer-logo">Trader Journal</a>
          <div class="add">10 000 hours starts here. Dedicated practice delivers results.</div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="links">
          <div class="heading">Links</div>
          <div class="links_menu">
            <ul>
              <li><a href="<%=request.getContextPath() %>/trade/create">Create Trade</a></li>            
              <li><a href="<%=request.getContextPath() %>/account/accountList">Create Account</a></li>
              <li><!-- a href="#">Privacy Policy</a--></li>
              <li><!-- a href="#">Help.</a--></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="contact">
          <div class="heading">Contact</div>
          <label>Ph. 082-883-0052</label>
          <label>Email : <a href="mailto:traderjournal@raaskop.com" class="email">trade@raaskop.com</a></label>
          <div class="social"> <a href="#"><img src='<spring:url value="/resources/images/social_icon_1.png"/>' alt=""></a> <a href="#"><img src='<spring:url value="/resources/images/social_icon_2.png"/>' alt=""></a> <a href="#"><img src='<spring:url value="/resources/images/social_icon_3.png"/>' alt=""></a> <a href="#"><img src='<spring:url value="/resources/images/social_icon_4.png"/>' alt=""></a> </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="copyright">
  <div class="container"> © 2014 Trade Managment. All rights reserved</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#horizontalTab').easyResponsiveTabs({
            type: 'default', //Types: default, vertical, accordion           
            width: 'auto', //auto or any width like 600px
            fit: true,   // 100% fit in a container
            closed: 'accordion', // Start closed if in accordion view
            activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#tabInfo');
                var $name = $('span', $info);

                $name.text($tab.text());

                $info.show();
            }
        });

        $('#verticalTab').easyResponsiveTabs({
            type: 'vertical',
            width: 'auto',
            fit: true
        });
    });
</script>
<!-- JS -->
<!-- JS ends -->