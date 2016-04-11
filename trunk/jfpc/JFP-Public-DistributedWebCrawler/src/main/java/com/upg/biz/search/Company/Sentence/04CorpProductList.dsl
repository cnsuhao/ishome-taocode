<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_product_list</id>
		<index>corp_product_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			        "filtered":{
				        "query": {
					        "bool":{
						        "must":[
						        	{"match" : {"product" : "%s"}},
						        	{"match" : {"res_date" : "%s"}}
						        ]
					        }
					    },
					    "filter":{
					        "range":{
					        	"corp_rc" : {"gte" : %s,"lte" : %s},
					        	"corp_edate" : {"gte" : "%s","lte" : "%s"}
					        }
					    }
			        }
			    },
			    "from":%s,
			    "size":%s
			}
			]]>
		</dsl>
	</query>
</sentences>