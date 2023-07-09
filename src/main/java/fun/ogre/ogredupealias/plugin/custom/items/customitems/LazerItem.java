package fun.ogre.ogredupealias.plugin.custom.items.customitems;

import fun.ogre.ogredupealias.plugin.ItemPresets;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItem;
import fun.ogre.ogredupealias.plugin.custom.items.CustomItemInteractionCallback;
import fun.ogre.ogredupealias.utils.RaycastUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LazerItem extends CustomItem {

    public LazerItem() {
        super("lazer", ItemPresets.LAZER);
    }

    @Override
    public CustomItemInteractionCallback getCallback() {
        return (player, item, event) -> {
            event.setCancelled(true);

            Location loc = player.getLocation().add(0, 1.4, 0);
            Vector dir = player.getLocation().getDirection().normalize();
            AtomicInteger attempts = new AtomicInteger(0);

            castInDirection(loc, dir, true, 50, attempts);
        };
    }

    private void castInDirection(Location loc, Vector dir, boolean again, int maxAttempts, AtomicInteger attempts) {
        AtomicBoolean hitSmth = new AtomicBoolean(false);
        Location hit = RaycastUtils.raycast(loc, dir, 64, 0.1, point -> {
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 0.5F);
            point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
            hitSmth.set(!point.getBlock().isPassable() && loc.distance(point) > 1.0);
            return hitSmth.get();
        });
        Vector incoming = dir.clone().multiply(-1).normalize();
        Vector bounce = getContactingFace(hit, hit.getBlock());
        Vector reflect = bounce.multiply(-1).subtract(incoming).normalize();

        if (again && maxAttempts > attempts.getAndIncrement()) {
            castInDirection(hit, reflect, hitSmth.get(), maxAttempts, attempts);
        }
    }

    private Vector getContactingFace(Location pointOfContact, Block block) {
        Vector center = block.getLocation().add(0.5, 0.5, 0.5).toVector();
        Vector incoming = center.subtract(pointOfContact.toVector());
        double x = incoming.dot(new Vector(1, 0, 0));
        double y = incoming.dot(new Vector(0, 1, 0));
        double z = incoming.dot(new Vector(0, 0, 1));
        double ax = Math.abs(x);
        double ay = Math.abs(y);
        double az = Math.abs(z);

        if (ax > ay && ax > az)
            return new Vector(x, 0, 0).normalize();
        else if (ay > ax && ay > az)
            return new Vector(0, y, 0).normalize();
        else
            return new Vector(0, 0, z).normalize();
    }
}
