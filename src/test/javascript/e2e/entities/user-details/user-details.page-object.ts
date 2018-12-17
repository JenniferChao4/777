import { element, by, ElementFinder } from 'protractor';

export class UserDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-user-details div table .btn-danger'));
    title = element.all(by.css('jhi-user-details div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class UserDetailsUpdatePage {
    pageTitle = element(by.id('jhi-user-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    netWorthInput = element(by.id('field_netWorth'));
    totalCashInput = element(by.id('field_totalCash'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNetWorthInput(netWorth) {
        await this.netWorthInput.sendKeys(netWorth);
    }

    async getNetWorthInput() {
        return this.netWorthInput.getAttribute('value');
    }

    async setTotalCashInput(totalCash) {
        await this.totalCashInput.sendKeys(totalCash);
    }

    async getTotalCashInput() {
        return this.totalCashInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class UserDetailsDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-userDetails-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-userDetails'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
