package org.eclipse.leshan.client.demo;

import java.util.Arrays;
import java.util.List;

import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.client.servers.ServerIdentity;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.response.ReadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryAppData extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(BinaryAppData.class);

    private static final int DATA_UPLINK = 0;

    private static final int DATA_DOWNLINK = 1;

    private static final List<Integer> supportedResources = Arrays.asList(DATA_UPLINK, DATA_DOWNLINK);

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        if (!identity.isSystem())
            LOG.info("Read on Data resource /{}/{}/{}", getModel().id, getId(), resourceid);
        switch (resourceid) {
        case DATA_UPLINK:
            return ReadResponse.success(resourceid, getUplinkData());
        case DATA_DOWNLINK:
            return ReadResponse.success(resourceid, getDownlinkData());
        default:
            return super.read(identity, resourceid);
        }
    }

    private String getUplinkData() {
        return "aa009d2102d01222009018430c000506660006010100083836323635373035323438393534330009383938363131323232313130303734313834313600120100134b5a58444d30303030303834380000000000000000000000000000000000000020012f200207e608180a3519002003102004025002010250033131352e37382e39322e323533000000000000000000000000000000000035363835000cc3";
    }

    private String getDownlinkData() {
        return "OK";
    }

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }

}
