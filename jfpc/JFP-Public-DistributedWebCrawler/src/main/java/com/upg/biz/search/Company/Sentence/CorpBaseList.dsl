<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>CorpBaseList</id>
		<index>corp_base</index>
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