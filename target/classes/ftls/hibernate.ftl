<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping default-lazy="true">
    <!--${Title} -->
    <class name="${BasePackageName}${EntityPackageName}.${ClassName}" table="T_${ClassName}">
    ${ResultMap}
    </class>

</hibernate-mapping>