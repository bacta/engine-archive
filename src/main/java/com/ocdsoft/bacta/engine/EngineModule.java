package com.ocdsoft.bacta.engine;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.ocdsoft.bacta.engine.conf.BactaConfiguration;
import com.ocdsoft.bacta.engine.conf.ini.IniBactaConfiguration;
import com.ocdsoft.bacta.engine.service.objectfactory.NetworkObjectFactory;
import com.ocdsoft.bacta.engine.service.objectfactory.impl.GuiceNetworkObjectFactory;
import com.ocdsoft.bacta.engine.service.scheduler.SchedulerService;
import com.ocdsoft.bacta.engine.service.scheduler.TaskSchedulerService;


public class EngineModule extends AbstractModule implements Module {

	@Override
	protected void configure() {
        bind(NetworkObjectFactory.class).to(GuiceNetworkObjectFactory.class);
        bind(BactaConfiguration.class).to(IniBactaConfiguration.class);
        bind(SchedulerService.class).to(TaskSchedulerService.class);
    }

}
