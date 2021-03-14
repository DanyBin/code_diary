package until;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sankuai.meituan.waimai.d.traffic.remix.comp.thrift.ThriftRemixSingularVo;
import com.sankuai.meituan.waimai.d.traffic.unify.mix.ItemDim;
import com.sankuai.meituan.waimai.d.traffic.unify.mix.ItemId;
import com.sankuai.meituan.waimai.d.traffic.unify.ranking_regulation.regulation.stg.core.CommonItemRuleEngine;
import com.sankuai.meituan.waimai.d.traffic.unify.ranking_regulation.regulation.stg.core.domain.*;
import com.sankuai.meituan.waimai.d.traffic.unify.ranking_regulation.regulation.stg.core.scene.ProcessorTypeEnum;
import com.sankuai.meituan.waimai.d.traffic.unify.ranking_regulation.regulation.stg.core.scene.SceneTypeEnum;
import com.sankuai.meituan.waimai.d.traffic.unify.ranking_regulation.regulation.stg.core.util.DecisionParamConstants;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2020/11/4 下午3:21
 * @Decr TODO
 * @Link TODO
 **/
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        Map<String, Factor> decisionFactors = new HashMap<>();
        Factor factor = Factor.builder().name("factor_tid").expression("tid").build();
        decisionFactors.put(factor.getName(),factor);


        Map<String,Param> paramMap = new HashMap<>();
        //Param param = Param.builder().name(DecisionParamConstants.p__fixed_slot_array).value(1).order(0).build();
        Param param = Param.builder().name(DecisionParamConstants.p__min_space_count).value(3).order(1).build();
        paramMap.put(param.getName(),param);

        String exp = "1 == tid";
        SceneElement sceneElement = SceneElement.builder()
                //.filterExpression(exp) //打压
                .decisionFactors(decisionFactors) //决策因子
                .decisionParams(paramMap)      //具体参数
                .decisionExpression("true")    //决策规则
                .build();
        List<SceneElement> sceneElements = Lists.newArrayList();
        sceneElements.add(sceneElement);


//        Scene scene = Scene.builder()
//                .sceneElementList(sceneElements)
//                .sceneType(SceneTypeEnum.QUANTITY_ASSURANCE_FIXED_LOCATION.getType())
//                .build();
        Scene scene = Scene.builder()
                .sceneElementList(sceneElements)
                .sceneType(SceneTypeEnum.SCATTER_BY_DYNAMIC_WINDOW_SPACE_NUM.getType())
                .build();

        List<Scene> sceneList = Lists.newArrayList();
        sceneList.add(scene);

//        SceneGroup sceneGroup = SceneGroup.builder()
//                .groupType(ProcessorTypeEnum.ITEM_QUANTITY_ASSURANCE.getType())
//                .sceneList(sceneList)
//                .build();
        SceneGroup sceneGroup = SceneGroup.builder()
                .groupType(ProcessorTypeEnum.ITEM_MOVE.getType())
                .sceneList(sceneList)
                .build();

        List<SceneGroup> sceneGroups = Lists.newArrayList();
        sceneGroups.add(sceneGroup);

        RequestConfig requestConfig = RequestConfig.builder()
                .sceneGroupList(sceneGroups)
                .isDebugEnable(true)
                .build();
        //准备数据
        Map<Long,Map<String,Object>> mapMap = Maps.newHashMap();

        List<Long> list = Lists.newArrayList();
        list.add(1L);
        mapMap.put(1L, ImmutableMap.of("tid",0));
        //mapMap.put(1L,ImmutableMap.of("index",0));

        list.add(2L);
        //mapMap.put(2L, ImmutableMap.of("tid",1));
        //mapMap.put(1L,ImmutableMap.of("index",1));


        list.add(3L);
        //mapMap.put(3L, ImmutableMap.of("tid",0));
        //mapMap.put(1L,ImmutableMap.of("index",2));

        list.add(4L);
        mapMap.put(4L, ImmutableMap.of("tid",1));
        //mapMap.put(1L,ImmutableMap.of("index",3));

        list.add(5L);
        mapMap.put(5L, ImmutableMap.of("tid",1));

        list.add(6L);
        mapMap.put(6L, ImmutableMap.of("tid",5));

        list.add(7L);
        mapMap.put(7L, ImmutableMap.of("tid",6));

        list.add(8L);
        mapMap.put(8L, ImmutableMap.of("tid",7));


        CommonItemRuleEngine<Long> commonItemRuleEngine = new CommonItemRuleEngine<>();
        List<Long> execute = commonItemRuleEngine.execute(list, mapMap, requestConfig);
        System.out.println(execute.toString());
    }

}
