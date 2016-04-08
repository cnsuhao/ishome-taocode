<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_base_list</id>
		<index>corp_base_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			            "match" : {
			                    "corp_name" : "%s"
			        },
			     "size": 15   
			    }
			}
			]]>
		</dsl>
	</query>
</sentences>