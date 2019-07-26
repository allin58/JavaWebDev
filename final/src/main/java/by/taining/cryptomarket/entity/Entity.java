package by.taining.cryptomarket.entity;


/**
 * The abstract class for all entities.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
abstract public class Entity {
    /**
     * The identity of entity.
     */
    private Integer identity;

    /**
     * The getter of identity.
     * @return identity
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * The setter of identity.
     * @param identity
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * The override method.
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if(object != null) {
            if(object != this) {
                if(object.getClass() == getClass() && identity != null) {
                    return identity.equals(((Entity)object).identity);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * The override method.
     * @return int
     */
    @Override
    public int hashCode() {
        return identity != null ? identity.hashCode() : 0;
    }
}
