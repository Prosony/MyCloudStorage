
const ZERO = 0;

const price_begin = 100;
const price_end = 10000;

const regionsIdDilling = [29790,36118,36117,45838,45839,36120,52845,52852,52849,52857,52847,52844,29972,26911,33030,29259,29256,29257,31375,31371,31377,31383,31473,31475,35211,35212,35213,38873,31373,31379,31381,31471,41172,36100,36119,36312,36105,36632,36852,37316,37317,37318,37972,38411,38412,40011,41391,42791,45251,45836]
let lengthRegionsId = regionsIdDilling.length;


export default {

    change_contract (contract){
        contract.regionId = regionsIdDilling[randomInt(ZERO, lengthRegionsId)];
        contract.statusCode = ZERO;
    },

    change_internet_accounts (json) {

        let internetCost = randomInt(price_begin,price_end);

        //internetAccounts
        let internet_accounts_lenght = json.internetAccounts.internetAccounts.length;
        for (let count = 0; count < internet_accounts_lenght; count++) {
            let id_tariff_plan = parseInt(json.internetAccounts.internetAccounts[count].internetAccount.tariffPlan.id);
            json.internetAccounts.internetAccounts[count].internetAccount.tariffPlan.cost = internetCost;
            cycle_internet_account(json[`internetTariffs_${json.internetAccounts.internetAccounts[count].internetAccount.id}`], internetCost, id_tariff_plan);
            cycle_internet_account(json[`internetTariffs`], internetCost, id_tariff_plan);
        }
    },

    change_tv_account (json){
        // let tv_account_id = json.tvAccounts.tvAccounts[]


    }
}

function cycle_internet_account(internet_tariffs, internetCost, id_tariff_plan) {
    if (internet_tariffs !== undefined){
        let tariff_plans_length = internet_tariffs.tariffPlans.length;
        for (let count = 0; count < tariff_plans_length; count++) {
            if (internet_tariffs.tariffPlans[count].tariffPlan.id === id_tariff_plan) {
                internet_tariffs.tariffPlans[count].tariffPlan.cost = internetCost;
                break;
            }
        }
    }

}

function randomInt (low, high) {
    return Math.floor(Math.random() * (high - low) + low)
}