package org.spo.svc.pages.gateway.svc;

//
//
//@Bean
//public DefaultMessageListenerContainer jmsListenerContainer() {
//    DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
//    dmlc.setConnectionFactory(connectionFactory());
//    dmlc.setDestination(new ActiveMQQueue("MAIN.2"));
//
//    // To schedule our concurrent listening tasks
//    dmlc.setTaskExecutor(taskExecutor());
//
//    // To perform actual message processing
//    dmlc.setMessageListener(messageListener());
//
//    dmlc.setConcurrentConsumers(10);
//
//    // ... more parameters that you might want to inject ...
//    return dmlc;
//}