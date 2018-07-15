<fieldset>
    <legend>Select Log Level</legend>
    <select name="levels" id="levels" size="11">
        <option value="OFF">OFF</option>
        <option value="FATAL">FATAL</option>
        <option value="CONTROLLER">CONTROLLER</option>
        <option value="SERVICE">SERVICE</option>
        <option value="REPOSITORY">REPOSITORY</option>
        <option value="ERROR">ERROR</option>
        <option value="INFO">INFO</option>
        <option value="DEBUG">DEBUG</option>
        <option value="WARN">WARN</option>
        <option value="TRACE">TRACE</option>
        <option value="ALL">ALL</option>
    </select>
</fieldset>
<br />
<fieldset>
    <legend>Select Logger Name</legend>
    <select name="logger" id="logger">
        <option value="ROOT">root logger</option>
        <option value="net.sf.ehcache">ehcache logger</option>
        <option value="org.hibernate">hibernate logger</option>
        <option value="com.nyp.shopping">application logger</option>
        <option value="org.springframework">springframework logger</option>
    </select>
</fieldset>
<br />
<div align="center">
	<input type="button" value="SUBMIT" onclick="setLogLevel()">
</div>
