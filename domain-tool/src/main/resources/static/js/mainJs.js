/**
 * Created by WANGXIJUN on 2014/12/13.
 */
$(function(){

    $(window).resize(function () {
        $('table').bootstrapTable('resetView');
    });

    $('#bt_filter').click(function(){
        $('.filterPanel').slideToggle(500);
    });

    $('#bt_create').click(DomainBuilder.checkFilter);

    $('#bt_query').click(QueryDomain.queryDomain);

    $('#bt_storeQuery').click(storeDomain.storeResult);

    $('#bt_history').click(storeDomain.queryHistory);

    $("input:radio[name=count]").change(function(){
        $('.placeContainer').empty();
        var count = $(this).val();
        for(var i = 1; i <= count; i++){
            $('.placeContainer').append("<textarea class='placeNode' placeholder=请输入第"+i+"位值></textarea>")
        }
        $('.placeContainer textarea').keyup(DomainBuilder.checkPlace);
    });

    DomainBuilder.loadPinYinDomain();
    QueryDomain.socketInit();
});

var DomainBuilder = {

    domainContainer:[{sid:'',domainname:''}],

    pinyinDomain:[],

    digitDomain:[0,1,2,3,4,5,6,7,8,9],

    reset:function(){
        DomainBuilder.domainContainer = [{sid:'',domainname:''}];
    },

    loadPinYinDomain:function(){
        $.get('data/yinjie.txt',function(data){
            DomainBuilder.pinyinDomain = data.split(',');
        });
    },

    checkPlace:function(event){
        var val = $(event.target).val();
        if(val === "*") return;
        var typeVal = $('input:radio[name=type]:checked').val();
        if(typeVal === "2"){
            var result = val.match(/\d{1}/);
            if(val.length == 1 && result != null){
                $(event.target).removeClass('error');
            }else{
                $(event.target).addClass('error');
            }
        }
    },

    checkFilter:function(){
        var places = $('.placeContainer .error');
        if(places.length != 0){
            alert('请检查输入内容！');
        }else if($('#suffixname').val() === ""){
            alert('请检查后缀名称！');
        }else{
            DomainBuilder.reset();
            DomainBuilder.createDomain();
        }
    },

    createDomain:function(){
        var placeVals = $('.placeContainer textarea').map(function(){
            var val = $(this).val();
            var result = [];
            result.push(val.split(','));
            return result;
        });

        var typeVal = $('input:radio[name=type]:checked').val();
        for(var i=0; i < placeVals.length;i++){
            var val0 = placeVals[i],temp = val0,tempContainer=[];
            if(val0[0] === "" || val0[0] === "*"){
                if(typeVal === "1")
                    temp = DomainBuilder.pinyinDomain;
                else
                    temp = DomainBuilder.digitDomain;
            }

            for(var j = 0; j < DomainBuilder.domainContainer.length; j++){
                var origin = DomainBuilder.domainContainer[j];
                for(var k = 0; k < temp.length; k++){
                    var next = temp[k];
                    tempContainer.push({
                        domainname: origin.domainname + next
                    });
                }
            }
            DomainBuilder.reset();
            DomainBuilder.domainContainer = tempContainer;
            tempContainer = [];
        }
        if(DomainBuilder.domainContainer.length > 501){
            alert('产生域名超过500，请精确条件！');
            return;
        }
        var suffixName = $('#suffixname').val();
        for(var index = 0; index < DomainBuilder.domainContainer.length; index++){
            var item = DomainBuilder.domainContainer[index];
            item.sid = index;
            item.domainname = item.domainname+suffixName;
        }
        $('table').bootstrapTable('load',DomainBuilder.domainContainer);
    }
};

var QueryDomain = {

    stompClient:null,

    btModalSet:function(tag){
        if(tag){
            $("button").addClass(".disabled");
        }else{
            $("button").removeClass(".disabled");
        }
    },

    socketInit:function(){
        var socket = new SockJS('/domainscan');
        QueryDomain.stompClient = Stomp.over(socket);
        QueryDomain.stompClient.connect({}, function(frame) {

            console.log('Connected: ' + frame);
            QueryDomain.stompClient.subscribe('/topic/bulkquery', function(responseData){

                var msg = JSON.parse(responseData.body);
                if(msg.status == "BEGIN"){
                    QueryDomain.btModalSet(true);
                }else if(msg.status == "QUERYING"){
                    var domain = msg.message;
                    $('table').bootstrapTable('updateRow', {
                        index: domain.sid,
                        row: {
                            registerStatus:  domain.registerStatus,
                            createDate:      domain.createDate,
                            expirationDate:  domain.expirationDate,
                            registrant:      domain.registrant,
                            registrantEmail: domain.registrantEmail,
                            registrar:       domain.registrar
                        }
                    });
                }else if(msg.status == "DONE"){
                    alert("查询完成！");
                    QueryDomain.btModalSet(false);
                }

            });
        });

    },
    cancelReceive:function(){
        if(QueryDomain.stompClient)
            QueryDomain.stompClient.disconnect();
    },
    queryDomain:function(){
        try{
            console.log(JSON.stringify(DomainBuilder.domainContainer));
            QueryDomain.stompClient.send("/app/domainscan", {}, JSON.stringify(DomainBuilder.domainContainer));
        }catch (e){
            console.log(e);
        }

    }
};

var storeDomain = {

    storeResult:function(){
        var name = $('#storeName').val();
        if(name == ""){
            name = (new Date()).toDateString();
        }
        var obj = {
            "name":name,
            "data":DomainBuilder.domainContainer
        };
		
		
        console.log(JSON.stringify(obj));
		/**
        $.post("/savequeries",JSON.stringify(obj),function(data) {
            alert(JSON.stringify(data));
        },"text");*/
		
		$.ajax({ 
	url: "/savequeries", 
	data:JSON.stringify(obj),
	contentType:"text/plain",
    type: 'POST', 
    mimeType: "text/plain",
    success: function(data) {
       alert(JSON.stringify(data));
    },
    error:function(data,status,er) { 
    	alert(JSON.stringify(data));
        alert("error: "+data+" status: "+status+" er:"+er);
    }
});



		
		/**
		$.post("/savequeries",obj,function(data){
            alert(JSON.stringify(data));
        });*/
    },

    queryHistory:function(){
        $('#hisDialog').modal('show');
        $.get("/queries",function(data){
            if(data.length == 0){
                alert("暂无历史数据");
            }else{
                var result = JSON.parse(data);
                $.each(result,function(index,item){
                    $('#hisList').append("<li class='list-group-item' data-url="+item.url+">"+item.name+"</li>");
                });
                $('#hisList>li').click(storeDomain.showHistory);
            }

        })
    },

    showHistory:function(event){
        $('#hisDialog').modal('hide');
        var url = event.target.dataset.url;
        $.getJSON(url,function(data){
            DomainBuilder.domainContainer = data;
            $('table').bootstrapTable('load',DomainBuilder.domainContainer);
        })
    }
};