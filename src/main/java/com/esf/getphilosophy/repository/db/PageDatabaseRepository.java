package com.esf.getphilosophy.repository.db;

import org.springframework.data.repository.CrudRepository;

import com.esf.getphilosophy.domain.Page;

public interface PageDatabaseRepository extends CrudRepository<Page, Long>{

	public Page findByUrl(String url);
	
}
