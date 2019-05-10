import java.util.logging.Logger;

public class IvoryTower implements WizardTower {

    private static final Logger LOGGER = LoggerFactory.getLogger(IvoryTower.class);

    public void enter(Wizard wizard) {

        LOGGER.info("{} enters the tower.", wizard);
    }

}

