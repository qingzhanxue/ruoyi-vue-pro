package cn.iocoder.yudao.module.iot.service.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.number.NumberUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import cn.iocoder.yudao.framework.common.util.spring.SpringExpressionUtils;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import cn.iocoder.yudao.module.iot.dal.dataobject.rule.IotRuleSceneDO;
import cn.iocoder.yudao.module.iot.dal.mysql.rule.IotRuleSceneMapper;
import cn.iocoder.yudao.module.iot.enums.device.IotDeviceMessageIdentifierEnum;
import cn.iocoder.yudao.module.iot.enums.device.IotDeviceMessageTypeEnum;
import cn.iocoder.yudao.module.iot.enums.rule.IotRuleSceneActionTypeEnum;
import cn.iocoder.yudao.module.iot.enums.rule.IotRuleSceneTriggerConditionParameterOperatorEnum;
import cn.iocoder.yudao.module.iot.enums.rule.IotRuleSceneTriggerTypeEnum;
import cn.iocoder.yudao.module.iot.mq.message.IotDeviceMessage;
import cn.iocoder.yudao.module.iot.service.rule.action.IotRuleSceneAction;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.filterList;

/**
 * IoT 规则场景 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class IotRuleSceneServiceImpl implements IotRuleSceneService {

    @Resource
    private IotRuleSceneMapper ruleSceneMapper;

    @Resource
    private List<IotRuleSceneAction> ruleSceneActions;

    // TODO 芋艿，缓存待实现
    @Override
    @TenantIgnore // 忽略租户隔离：因为 IotRuleSceneMessageHandler 调用时，一般未传递租户，所以需要忽略
    public List<IotRuleSceneDO> getRuleSceneListByProductKeyAndDeviceNameFromCache(String productKey, String deviceName) {
        if (true) {
            IotRuleSceneDO ruleScene01 = new IotRuleSceneDO();
            ruleScene01.setTriggers(CollUtil.newArrayList());
            IotRuleSceneDO.TriggerConfig trigger01 = new IotRuleSceneDO.TriggerConfig();
            trigger01.setType(IotRuleSceneTriggerTypeEnum.DEVICE.getType());
            trigger01.setConditions(CollUtil.newArrayList());
            // 属性
            IotRuleSceneDO.TriggerCondition condition01 = new IotRuleSceneDO.TriggerCondition();
            condition01.setType(IotDeviceMessageTypeEnum.PROPERTY.getType());
            condition01.setIdentifier(IotDeviceMessageIdentifierEnum.PROPERTY_REPORT.getIdentifier());
            condition01.setParameters(CollUtil.newArrayList());
//            IotRuleSceneDO.TriggerConditionParameter parameter010 = new IotRuleSceneDO.TriggerConditionParameter();
//            parameter010.setIdentifier("width");
//            parameter010.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.EQUALS.getOperator());
//            parameter010.setValue("abc");
//            condition01.getParameters().add(parameter010);
            IotRuleSceneDO.TriggerConditionParameter parameter011 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter011.setIdentifier("width");
            parameter011.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.EQUALS.getOperator());
            parameter011.setValue("1");
            condition01.getParameters().add(parameter011);
            IotRuleSceneDO.TriggerConditionParameter parameter012 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter012.setIdentifier("width");
            parameter012.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.NOT_EQUALS.getOperator());
            parameter012.setValue("2");
            condition01.getParameters().add(parameter012);
            IotRuleSceneDO.TriggerConditionParameter parameter013 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter013.setIdentifier("width");
            parameter013.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.GREATER_THAN.getOperator());
            parameter013.setValue("0");
            condition01.getParameters().add(parameter013);
            IotRuleSceneDO.TriggerConditionParameter parameter014 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter014.setIdentifier("width");
            parameter014.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.GREATER_THAN_OR_EQUALS.getOperator());
            parameter014.setValue("0");
            condition01.getParameters().add(parameter014);
            IotRuleSceneDO.TriggerConditionParameter parameter015 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter015.setIdentifier("width");
            parameter015.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.LESS_THAN.getOperator());
            parameter015.setValue("2");
            condition01.getParameters().add(parameter015);
            IotRuleSceneDO.TriggerConditionParameter parameter016 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter016.setIdentifier("width");
            parameter016.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.LESS_THAN_OR_EQUALS.getOperator());
            parameter016.setValue("2");
            condition01.getParameters().add(parameter016);
            IotRuleSceneDO.TriggerConditionParameter parameter017 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter017.setIdentifier("width");
            parameter017.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.IN.getOperator());
            parameter017.setValue("1,2,3");
            condition01.getParameters().add(parameter017);
            IotRuleSceneDO.TriggerConditionParameter parameter018 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter018.setIdentifier("width");
            parameter018.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.NOT_IN.getOperator());
            parameter018.setValue("0,2,3");
            condition01.getParameters().add(parameter018);
            IotRuleSceneDO.TriggerConditionParameter parameter019 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter019.setIdentifier("width");
            parameter019.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.BETWEEN.getOperator());
            parameter019.setValue("1,3");
            condition01.getParameters().add(parameter019);
            IotRuleSceneDO.TriggerConditionParameter parameter020 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter020.setIdentifier("width");
            parameter020.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.NOT_BETWEEN.getOperator());
            parameter020.setValue("2,3");
            condition01.getParameters().add(parameter020);
            trigger01.getConditions().add(condition01);
            // 状态
            IotRuleSceneDO.TriggerCondition condition02 = new IotRuleSceneDO.TriggerCondition();
            condition02.setType(IotDeviceMessageTypeEnum.STATE.getType());
            condition02.setIdentifier(IotDeviceMessageIdentifierEnum.STATE_ONLINE.getIdentifier());
            condition02.setParameters(CollUtil.newArrayList());
            trigger01.getConditions().add(condition02);
            // 事件
            IotRuleSceneDO.TriggerCondition condition03 = new IotRuleSceneDO.TriggerCondition();
            condition03.setType(IotDeviceMessageTypeEnum.EVENT.getType());
            condition03.setIdentifier("xxx");
            condition03.setParameters(CollUtil.newArrayList());
            IotRuleSceneDO.TriggerConditionParameter parameter030 = new IotRuleSceneDO.TriggerConditionParameter();
            parameter030.setIdentifier("width");
            parameter030.setOperator(IotRuleSceneTriggerConditionParameterOperatorEnum.EQUALS.getOperator());
            parameter030.setValue("1");
            trigger01.getConditions().add(condition03);
            ruleScene01.getTriggers().add(trigger01);
            // 动作
            ruleScene01.setActions(CollUtil.newArrayList());
            IotRuleSceneDO.ActionConfig action01 = new IotRuleSceneDO.ActionConfig();
            action01.setType(IotRuleSceneActionTypeEnum.DEVICE_CONTROL.getType());
            IotRuleSceneDO.ActionDeviceControl actionDeviceControl01 = new IotRuleSceneDO.ActionDeviceControl();
            actionDeviceControl01.setProductKey("4aymZgOTOOCrDKRT");
            actionDeviceControl01.setDeviceNames(ListUtil.of("small"));
            actionDeviceControl01.setType(IotDeviceMessageTypeEnum.PROPERTY.getType());
            actionDeviceControl01.setIdentifier(IotDeviceMessageIdentifierEnum.PROPERTY_SET.getIdentifier());
            actionDeviceControl01.setData(MapUtil.<String, Object>builder()
                    .put("power", 1)
                    .put("color", "red")
                    .build());
            action01.setDeviceControl(actionDeviceControl01);
            ruleScene01.getActions().add(action01);
            return ListUtil.toList(ruleScene01);
        }

        List<IotRuleSceneDO> list = ruleSceneMapper.selectList();
        // TODO @芋艿：需要考虑开启状态
        return filterList(list, ruleScene -> {
            for (IotRuleSceneDO.TriggerConfig trigger : ruleScene.getTriggers()) {
                if (ObjUtil.notEqual(trigger.getProductKey(), productKey)) {
                    continue;
                }
                if (CollUtil.isEmpty(trigger.getDeviceNames()) // 无设备名称限制
                        || trigger.getDeviceNames().contains(deviceName)) { // 包含设备名称
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public void executeRuleSceneByDevice(IotDeviceMessage message) {
        TenantUtils.execute(message.getTenantId(), () -> {
            // 1. 获得设备匹配的规则场景
            List<IotRuleSceneDO> ruleScenes = getMatchedRuleSceneList(message);
            if (CollUtil.isEmpty(ruleScenes)) {
                return;
            }

            // 2. 执行规则场景
            executeRuleSceneAction(message, ruleScenes);
        });
    }

    /**
     * 获得匹配的规则场景列表
     *
     * @param message 设备消息
     * @return 规则场景列表
     */
    private List<IotRuleSceneDO> getMatchedRuleSceneList(IotDeviceMessage message) {
        // 1. 匹配设备
        // TODO @芋艿：可能需要 getSelf(); 缓存
        List<IotRuleSceneDO> ruleScenes = getRuleSceneListByProductKeyAndDeviceNameFromCache(
                message.getProductKey(), message.getDeviceName());
        if (CollUtil.isEmpty(ruleScenes)) {
            return ruleScenes;
        }

        // 2. 匹配 trigger 触发器的条件
        return filterList(ruleScenes, ruleScene -> {
            for (IotRuleSceneDO.TriggerConfig trigger : ruleScene.getTriggers()) {
                // 2.1 非设备触发，不匹配
                if (ObjUtil.notEqual(trigger.getType(), IotRuleSceneTriggerTypeEnum.DEVICE.getType())) {
                    return false;
                }
                // TODO 芋艿：产品、设备的匹配，要不要这里在做一次？？？貌似和 1. 部分重复了
                // 2.2 条件为空，说明没有匹配的条件，因此不匹配
                if (CollUtil.isEmpty(trigger.getConditions())) {
                    return false;
                }
                // 2.3 多个条件，只需要满足一个即可
                IotRuleSceneDO.TriggerCondition matchedCondition = CollUtil.findOne(trigger.getConditions(), condition -> {
                    if (ObjUtil.notEqual(message.getType(), condition.getType())
                            || ObjUtil.notEqual(message.getIdentifier(), condition.getIdentifier())) {
                        return false;
                    }
                    // 多个条件参数，必须全部满足。所以，下面的逻辑就是找到一个不满足的条件参数
                    IotRuleSceneDO.TriggerConditionParameter notMatchedParameter = CollUtil.findOne(condition.getParameters(),
                            parameter -> !isTriggerConditionParameterMatched(message, parameter, ruleScene, trigger));
                    return notMatchedParameter == null;
                });
                if (matchedCondition == null) {
                    return false;
                }
                log.info("[getMatchedRuleSceneList][消息({}) 匹配到规则场景编号({}) 的触发器({})]", message, ruleScene.getId(), trigger);
                return true;
            }
            return false;
        });
    }

    // TODO @芋艿：【可优化】可以考虑增加下单测，边界太多了。
    /**
     * 判断触发器的条件参数是否匹配
     *
     * @param message   设备消息
     * @param parameter 触发器条件参数
     * @param ruleScene 规则场景（用于日志，无其它作用）
     * @param trigger   触发器（用于日志，无其它作用）
     * @return 是否匹配
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue"})
    private boolean isTriggerConditionParameterMatched(IotDeviceMessage message, IotRuleSceneDO.TriggerConditionParameter parameter,
                                                       IotRuleSceneDO ruleScene, IotRuleSceneDO.TriggerConfig trigger) {
        // 1.1 校验操作符是否合法
        IotRuleSceneTriggerConditionParameterOperatorEnum operator =
                IotRuleSceneTriggerConditionParameterOperatorEnum.operatorOf(parameter.getOperator());
        if (operator == null) {
            log.error("[isTriggerConditionParameterMatched][规则场景编号({}) 的触发器({}) 存在错误的操作符({})]",
                    ruleScene.getId(), trigger, parameter.getOperator());
            return false;
        }
        // 1.2 校验消息是否包含对应的值
        String messageValue = MapUtil.getStr((Map<String, Object>) message.getData(), parameter.getIdentifier());
        if (messageValue == null) {
            return false;
        }

        // 2.1 构建 Spring 表达式的变量
        Map<String, Object> springExpressionVariables = new HashMap<>();
        try {
            springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_SOURCE, messageValue);
            springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_VALUE, parameter.getValue());
            List<String> parameterValues = StrUtil.splitTrim(parameter.getValue(), CharPool.COMMA);
            springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_VALUE_List, parameterValues);
            // 特殊：解决数字的比较。因为 Spring 是基于它的 compareTo 方法，对数字的比较存在问题！
            if (ObjectUtils.equalsAny(operator, IotRuleSceneTriggerConditionParameterOperatorEnum.BETWEEN,
                    IotRuleSceneTriggerConditionParameterOperatorEnum.NOT_BETWEEN,
                    IotRuleSceneTriggerConditionParameterOperatorEnum.GREATER_THAN,
                    IotRuleSceneTriggerConditionParameterOperatorEnum.GREATER_THAN_OR_EQUALS,
                    IotRuleSceneTriggerConditionParameterOperatorEnum.LESS_THAN,
                    IotRuleSceneTriggerConditionParameterOperatorEnum.LESS_THAN_OR_EQUALS)
                    && NumberUtil.isNumber(messageValue)
                    && NumberUtils.isAllNumber(parameterValues)) {
                springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_SOURCE,
                        NumberUtil.parseDouble(messageValue));
                springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_VALUE,
                        NumberUtil.parseDouble(parameter.getValue()));
                springExpressionVariables.put(IotRuleSceneTriggerConditionParameterOperatorEnum.SPRING_EXPRESSION_VALUE_List,
                        convertList(parameterValues, NumberUtil::parseDouble));
            }
            // 2.2 计算 Spring 表达式
            return (Boolean) SpringExpressionUtils.parseExpression(operator.getSpringExpression(), springExpressionVariables);
        } catch (Exception e) {
            log.error("[isTriggerConditionParameterMatched][消息({}) 规则场景编号({}) 的触发器({}) 的匹配表达式({}/{}) 计算异常]",
                    message, ruleScene.getId(), trigger, operator, springExpressionVariables, e);
            return false;
        }
    }

    /**
     * 执行规则场景的动作
     *
     * @param message    设备消息
     * @param ruleScenes 规则场景列表
     */
    private void executeRuleSceneAction(IotDeviceMessage message, List<IotRuleSceneDO> ruleScenes) {
        // 1. 遍历规则场景
        ruleScenes.forEach(ruleScene -> {
            // 2. 遍历规则场景的动作
            ruleScene.getActions().forEach(actionConfig -> {
                // 3.1 获取对应的动作 Action 数组
                List<IotRuleSceneAction> actions = filterList(ruleSceneActions,
                        action -> action.getType().getType().equals(actionConfig.getType()));
                if (CollUtil.isEmpty(actions)) {
                    return;
                }
                // 3.2 执行动作
                actions.forEach(action -> {
                    try {
                        action.execute(message, actionConfig);
                        log.info("[executeRuleSceneAction][消息({}) 规则场景编号({}) 的执行动作({}) 成功]",
                                message, ruleScene.getId(), actionConfig);
                    } catch (Exception e) {
                        log.error("[executeRuleSceneAction][消息({}) 规则场景编号({}) 的执行动作({}) 执行异常]",
                                message, ruleScene.getId(), actionConfig, e);
                    }
                });
            });
        });
    }

}
