package bacta;

import bacta.conf.BactaConfiguration;
import bacta.conf.ini.IniBactaConfiguration;
import bacta.network.service.objectfactory.NetworkObjectFactory;
import bacta.network.service.objectfactory.impl.GuiceNetworkObjectFactory;
import bacta.network.service.scheduler.SchedulerService;
import bacta.network.service.scheduler.TaskSchedulerService;
import com.google.inject.AbstractModule;
import com.google.inject.Module;


public class EngineModule extends AbstractModule implements Module {

	@Override
	protected void configure() {
        bind(NetworkObjectFactory.class).to(GuiceNetworkObjectFactory.class);
        bind(BactaConfiguration.class).to(IniBactaConfiguration.class);
        bind(SchedulerService.class).to(TaskSchedulerService.class);
    }

}
