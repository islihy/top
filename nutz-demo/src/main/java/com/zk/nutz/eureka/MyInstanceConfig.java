package com.zk.nutz.eureka;

import com.netflix.appinfo.MyDataCenterInstanceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @ClassName: MyInstanceConfig
 * @Description:
 * @author SuXun
 * @date 2018年3月7日 上午9:33:31
 */
public class MyInstanceConfig extends MyDataCenterInstanceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MyInstanceConfig.class);
    @Override
    public String getHostName(boolean refresh) {

        try {
            return findFirstNonLoopbackAddress().getHostAddress();
        } catch (Exception e) {
            return super.getHostName(refresh);
        }
    }

    public InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;
        try {
            int lowest = Integer.MAX_VALUE;
            for (Enumeration<NetworkInterface> nics = NetworkInterface
                    .getNetworkInterfaces(); nics.hasMoreElements();) {
                NetworkInterface ifc = nics.nextElement();
                if (ifc.isUp()) {
                    LOG.trace("Testing interface: " + ifc.getDisplayName());
                    if (ifc.getIndex() < lowest || result == null) {
                        lowest = ifc.getIndex();
                    }
                    else if (result != null) {
                        continue;
                    }

                    // @formatter:off
                    for (Enumeration<InetAddress> addrs = ifc
                            .getInetAddresses(); addrs.hasMoreElements();) {
                        InetAddress address = addrs.nextElement();
                        if (address instanceof Inet4Address
                                && !address.isLoopbackAddress()) {
                            LOG.trace("Found non-loopback interface: "
                                    + ifc.getDisplayName());
                            result = address;
                        }
                    }
                    // @formatter:on
                }
            }
        }
        catch (IOException ex) {
            LOG.error("Cannot get first non-loopback address", ex);
        }

        if (result != null) {
            return result;
        }

        try {
            return InetAddress.getLocalHost();
        }
        catch (UnknownHostException e) {
            LOG.warn("Unable to retrieve localhost");
        }

        return null;
    }
}
